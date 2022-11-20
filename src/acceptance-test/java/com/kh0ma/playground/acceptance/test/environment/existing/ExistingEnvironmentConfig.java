package com.kh0ma.playground.acceptance.test.environment.existing;

import lombok.experimental.UtilityClass;

/**
 * @author <a href="mailto:khomenko.dp@gmail.com">Oleksandr Khomenko</a>
 * <br>
 */
@UtilityClass
public class ExistingEnvironmentConfig {

    public static final String DB_USERNAME = System.getProperty("acceptance.test.environment.existing.db.username", "postgres");
    public static final String DB_PASSWORD = System.getProperty("acceptance.test.environment.existing.db.password", "password");
    public static final String DB_JDBC_URL =
            System.getProperty("acceptance.test.environment.existing.db.jdbcurl", "jdbc:postgresql://localhost:25432/book");
    public static final String APP_BASE_URL =
            System.getProperty("acceptance.test.environment.existing.app.baseurl", "http://localhost:8080/api/v1");
}
