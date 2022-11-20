package com.kh0ma.playground.acceptance.test.environment.testcontainers;

import com.kh0ma.playground.acceptance.test.environment.TestEnvironmentDto;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import static com.kh0ma.playground.acceptance.test.environment.testcontainers.TestContainersEnvironmentConfig.JAVA_BASE_IMAGE;
import static com.kh0ma.playground.acceptance.test.environment.testcontainers.TestContainersEnvironmentConfig.SERVICE_JAR_FOLDER_PATH;
import static com.kh0ma.playground.acceptance.test.environment.testcontainers.TestContainersEnvironmentConfig.SERVICE_JAR_NAME;
import static com.kh0ma.playground.acceptance.test.environment.testcontainers.TestContainersEnvironmentConfig.KAFKA_IMAGE;
import static com.kh0ma.playground.acceptance.test.environment.testcontainers.TestContainersEnvironmentConfig.POSTGRES_IMAGE;
import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class TestContainersEnvironment {

    private static final Network network = Network.newNetwork();

    private static final PostgreSQLContainer<?> POSTGRES_SQL_CONTAINER =
        new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMAGE).asCompatibleSubstituteFor("postgres"))
            .withCreateContainerCmdModifier(createContainerCmd -> createContainerCmd.withName("postgres-" + RandomStringUtils.randomNumeric(10)))
            .withDatabaseName("acceptancetest")
            .withUsername("acceptancetest")
            .withNetworkAliases("db")
            .withNetwork(network)
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("postgres")))
            .withPassword("password");

    private static final KafkaContainer KAFKA_CONTAINER =
        new KafkaContainer(DockerImageName.parse(KAFKA_IMAGE).asCompatibleSubstituteFor("confluentinc/cp-kafka"))
            .withCreateContainerCmdModifier(createContainerCmd -> createContainerCmd.withName("kafka-" + RandomStringUtils.randomNumeric(10)))
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("kafka")))
            .withNetwork(network);

    private static final GenericContainer<?> APP_CONTAINER = new GenericContainer<>(JAVA_BASE_IMAGE)
            .withCreateContainerCmdModifier(createContainerCmd -> createContainerCmd.withName("service-app-" + RandomStringUtils.randomNumeric(10)))
            .withEnv("DB_HOSTNAME", env -> POSTGRES_SQL_CONTAINER.getNetworkAliases().get(0))
            .withEnv("DB_PORT", POSTGRESQL_PORT.toString())
            .withEnv("DB_DATABASE", env -> POSTGRES_SQL_CONTAINER.getDatabaseName())
            .withEnv("DB_USERNAME", env -> POSTGRES_SQL_CONTAINER.getUsername())
            .withEnv("DB_PASSWORD", env -> POSTGRES_SQL_CONTAINER.getPassword())
            .withEnv("DB_SCHEMA", "public")
            .withEnv("KAFKA_BOOTSTRAP_SERVERS", env -> KAFKA_CONTAINER.getNetworkAliases().get(0) + ":9092")
            .withFileSystemBind(SERVICE_JAR_FOLDER_PATH, "/app", BindMode.READ_ONLY)
            .withCommand("java", "-jar", "/app/" + SERVICE_JAR_NAME)
            .withNetwork(network)
            .withExposedPorts(8080, 8787)
            .waitingFor(new HttpWaitStrategy().forPort(8787).forPath("/healthcheck/liveness").forStatusCode(200))
            .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("service-app")))
            .dependsOn(POSTGRES_SQL_CONTAINER, KAFKA_CONTAINER);

    private static TestEnvironmentDto testEnvironment;

    public static synchronized TestEnvironmentDto get() {
        if (testEnvironment == null) {
            start();
            testEnvironment = TestEnvironmentDto.builder()
                    .appBaseUrl("http://localhost:" + APP_CONTAINER.getMappedPort(8080) + "/api/v1")
                    .dbJdbcUrl(POSTGRES_SQL_CONTAINER.getJdbcUrl())
                    .dbUser(POSTGRES_SQL_CONTAINER.getUsername())
                    .dbPassword(POSTGRES_SQL_CONTAINER.getPassword())
                    .build();
        }
        return testEnvironment;
    }

    private static void start() {
        APP_CONTAINER.start();
    }
}
