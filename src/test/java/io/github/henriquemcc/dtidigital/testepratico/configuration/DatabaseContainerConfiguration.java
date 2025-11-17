package io.github.henriquemcc.dtidigital.testepratico.configuration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class DatabaseContainerConfiguration {
    protected static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:9.1.0")
            .withDatabaseName("testdb")
            .withUsername("dtidigital")
            .withPassword("password");

    static {
        mysqlContainer.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.driverClassName", mysqlContainer::getDriverClassName);
    }
}
