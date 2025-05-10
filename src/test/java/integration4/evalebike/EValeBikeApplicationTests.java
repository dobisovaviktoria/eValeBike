package integration4.evalebike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test") // Ensure we're using the correct profile
public class EValeBikeApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
//        // Load the SQL script from src/test/resources/testdata.sql
//        try {
//            ClassPathResource script = new ClassPathResource("testdata.sql");
//            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//            databasePopulator.addScript(script);
//
//            // Execute the script against the connected Azure SQL Database
//            databasePopulator.populate(jdbcTemplate.getDataSource().getConnection());
//        } catch (Exception e) {
//            e.printStackTrace();  // In case of errors during script execution
//        }
    }
}
