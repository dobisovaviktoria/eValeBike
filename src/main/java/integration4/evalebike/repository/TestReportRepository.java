package integration4.evalebike.repository;
import integration4.evalebike.domain.TestReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestReportRepository extends JpaRepository<TestReport, String> {
}