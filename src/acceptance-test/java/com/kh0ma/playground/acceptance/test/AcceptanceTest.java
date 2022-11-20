package com.kh0ma.playground.acceptance.test;

import com.kh0ma.playground.acceptance.test.environment.EnvironmentFactory;
import com.kh0ma.playground.acceptance.test.environment.TestEnvironmentDto;
import com.kh0ma.playground.acceptance.test.example.api.client.ApiClient;
import com.kh0ma.playground.acceptance.test.util.ApiClientFactory;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
public abstract class AcceptanceTest {
    protected static TestEnvironmentDto environmentDto;

    protected final ApiClient apiClient = ApiClientFactory.getApiClient(environmentDto);

    @BeforeAll
    static void prepareEnvironment() {
        environmentDto = EnvironmentFactory.getEnvironment();
    }
}
