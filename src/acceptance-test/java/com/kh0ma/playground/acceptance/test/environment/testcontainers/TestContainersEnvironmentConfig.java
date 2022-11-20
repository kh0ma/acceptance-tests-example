package com.kh0ma.playground.acceptance.test.environment.testcontainers;

import lombok.experimental.UtilityClass;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class TestContainersEnvironmentConfig {

    public static final String POSTGRES_IMAGE = System.getProperty("acceptance.test.environment.testcontainers.image.postgres", "postgres:14.5");
    public static final String KAFKA_IMAGE = System.getProperty("acceptance.test.environment.testcontainers.image.kafka", "confluentinc/cp-kafka:7.0.0");
    public static final String JAVA_BASE_IMAGE = System.getProperty("acceptance.test.environment.testcontainers.image.java", "azul/zulu-openjdk:17");

    public static final String SERVICE_JAR_NAME = System.getProperty("acceptance.test.environment.testcontainers.service.jar.name", "service.jar");

    public static final String SERVICE_JAR_FOLDER_PATH = System.getProperty("acceptance.test.environment.testcontainers.service.jar.folder.path");
}
