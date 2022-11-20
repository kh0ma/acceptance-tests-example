package com.kh0ma.playground.acceptance.test.environment.existing;

import com.kh0ma.playground.acceptance.test.environment.TestEnvironmentDto;
import lombok.experimental.UtilityClass;

import static com.kh0ma.playground.acceptance.test.environment.existing.ExistingEnvironmentConfig.APP_BASE_URL;
import static com.kh0ma.playground.acceptance.test.environment.existing.ExistingEnvironmentConfig.DB_JDBC_URL;
import static com.kh0ma.playground.acceptance.test.environment.existing.ExistingEnvironmentConfig.DB_PASSWORD;
import static com.kh0ma.playground.acceptance.test.environment.existing.ExistingEnvironmentConfig.DB_USERNAME;


/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class ExistingEnvironment {

    private static TestEnvironmentDto testEnvironment;

    public static synchronized TestEnvironmentDto get() {
        if (testEnvironment == null) {
            testEnvironment = TestEnvironmentDto.builder()
                    .appBaseUrl(APP_BASE_URL)
                    .dbJdbcUrl(DB_JDBC_URL)
                    .dbUser(DB_USERNAME)
                    .dbPassword(DB_PASSWORD)
                    .build();
        }
        return testEnvironment;
    }
}
