package com.kh0ma.playground.acceptance.test.util;

import java.util.concurrent.TimeUnit;

import com.kh0ma.playground.acceptance.test.environment.TestEnvironmentDto;
import com.kh0ma.playground.acceptance.test.example.api.client.ApiClient;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class ApiClientFactory {
    private static final Long DEFAULT_TIMEOUT_CONNECT = 10000L;
    private static final Long DEFAULT_TIMEOUT_READ = 6000L;

    private static final Long DEFAULT_RETRY_FIRST_PERIOD = 100L;
    private static final Long DEFAULT_RETRY_MAX_PERIOD = 500L;
    private static final Integer DEFAULT_RETRY_MAX_ATTEMPTS = 3;

    public ApiClient getApiClient(TestEnvironmentDto testEnvironment) {
        ApiClient apiClient = getApiClient(testEnvironment.getAppBaseUrl());
//        String bearerToken = getBearerToken();
//        apiClient.setBearerToken(bearerToken);
        return apiClient;
    }

    private String getBearerToken() {
//        DefaultJwtBuilder defaultJwtBuilder = new DefaultJwtBuilder();
//        String userName = "acceptance-test-" + RandomStringUtils.randomAlphabetic(15);
//        String tenantId = RandomStringUtils.randomNumeric(15);
//        defaultJwtBuilder.setPayload(String.format(BEARER_TOKEN_PAYLOAD_FORMAT, userName, tenantId));
//        return defaultJwtBuilder.compact();
        throw new RuntimeException("Implement me first");
    }

    @NotNull
    private ApiClient getApiClient(String baseUrl) {
        ApiClient apiClient = new ApiClient();
        apiClient
                .getFeignBuilder()
                .logLevel(Logger.Level.FULL)
                .retryer(getRetryer())
                .options(getOptions());
        apiClient.setBasePath(baseUrl);
        return apiClient;
    }

    @NotNull
    private Retryer.Default getRetryer() {
        return new Retryer.Default(DEFAULT_RETRY_FIRST_PERIOD, DEFAULT_RETRY_MAX_PERIOD, DEFAULT_RETRY_MAX_ATTEMPTS);
    }

    @NotNull
    private Request.Options getOptions() {
        return new Request.Options(DEFAULT_TIMEOUT_CONNECT, TimeUnit.MILLISECONDS, DEFAULT_TIMEOUT_READ, TimeUnit.MILLISECONDS, true);
    }
}
