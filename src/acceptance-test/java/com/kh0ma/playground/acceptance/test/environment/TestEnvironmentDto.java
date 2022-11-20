package com.kh0ma.playground.acceptance.test.environment;

import lombok.Builder;
import lombok.Getter;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@Getter
@Builder
public class TestEnvironmentDto {
    private final String appBaseUrl;
    private final String dbJdbcUrl;
    private final String dbUser;
    private final String dbPassword;
}
