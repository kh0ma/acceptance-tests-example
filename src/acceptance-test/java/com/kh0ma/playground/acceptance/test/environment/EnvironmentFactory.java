package com.kh0ma.playground.acceptance.test.environment;

import com.kh0ma.playground.acceptance.test.environment.existing.ExistingEnvironment;
import com.kh0ma.playground.acceptance.test.environment.testcontainers.TestContainersEnvironment;
import lombok.experimental.UtilityClass;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class EnvironmentFactory {

    public static TestEnvironmentDto getEnvironment() {
        TestEnvironmentDto testEnvironment;
        String environmentType = System.getProperty("acceptance.test.environment.type", "existing");
        switch (environmentType) {
            case "existing":
                testEnvironment = EnvironmentFactory.getExistingEnvironment();
                break;
            case "testcontainers":
                testEnvironment = EnvironmentFactory.getTestContainersEnvironment();
                break;
            default:
                throw new IllegalArgumentException("This type of environment is not supported.");
        }
        return testEnvironment;
    }

    private static TestEnvironmentDto getExistingEnvironment() {
        return ExistingEnvironment.get();
    }

    private static TestEnvironmentDto getTestContainersEnvironment() {
        return TestContainersEnvironment.get();
    }

}
