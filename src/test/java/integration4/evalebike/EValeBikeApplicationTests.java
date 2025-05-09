package integration4.evalebike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active=test")
class EValeBikeApplicationTests {

    @Container
    static MSSQLServerContainer<?> sqlServerContainer = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2022-latest")
            .acceptLicense()
            .withEnv("SA_PASSWORD", "YourStrong!Passw0rd")
            .withUrlParam("encrypt", "true")
            .withUrlParam("trustServerCertificate", "true")
            .withExposedPorts(1433)
//            .withInitScript("static/testdata.sql")
            .waitingFor(Wait.forLogMessage(".*SQL Server is now ready for client connections.*\\n", 1));


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        String jdbcUrl = sqlServerContainer.getJdbcUrl();// <- Fix added here

        registry.add("spring.datasource.url", () -> jdbcUrl);
        registry.add("spring.datasource.username", sqlServerContainer::getUsername);
        registry.add("spring.datasource.password", sqlServerContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.SQLServerDialect");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");

        System.out.println("JDBC URL: " + jdbcUrl);
        System.out.println("Username: " + sqlServerContainer.getUsername());
        System.out.println("Password: " + sqlServerContainer.getPassword());
    }

    @Test
    void contextLoads() {
        // Just loads the Spring context
    }
}
