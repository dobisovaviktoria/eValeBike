package integration4.evalebike.service;

import integration4.evalebike.domain.TestReport;
import integration4.evalebike.repository.TestReportEntryRepository;
import integration4.evalebike.repository.TestReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TestReportService {
    private final TestReportRepository repo;
    private final TestReportEntryRepository repoTestReportEntry;

    public TestReportService(TestReportRepository repo, TestReportEntryRepository repoTestReportEntry) {
        this.repo = repo;
        this.repoTestReportEntry = repoTestReportEntry;
    }

    public List<TestReport> getAllReports(){
        return repo.findAll();
    }


    public TestReport getTestReportById(String testId) {
       var optionalReport = repo.findByIdWithEntries(testId);
        if (optionalReport.isEmpty()) {
            throw new RuntimeException("No TestReport found for testId: " + testId);
        }
        return optionalReport.get();
    }

}





