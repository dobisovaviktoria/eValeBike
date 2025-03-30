package integration4.evalebike.repository;


import integration4.evalebike.domain.TestReportEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestReportEntryRepository extends JpaRepository<TestReportEntry, Long> {
}