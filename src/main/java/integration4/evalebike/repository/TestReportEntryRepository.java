package integration4.evalebike.repository;


import integration4.evalebike.domain.TestReportEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestReportEntryRepository extends JpaRepository<TestReportEntry, Long> {
    List<TestReportEntry> findByTestReportId(String testReportId);
}