package integration4.evalebike.service;

import integration4.evalebike.domain.TestEvent;
import integration4.evalebike.domain.TestReport;
import integration4.evalebike.domain.TestReportEntry;
import integration4.evalebike.repository.TestEventRepository;
import integration4.evalebike.repository.TestReportEntryRepository;
import integration4.evalebike.repository.TestReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestReportService {
    private final TestReportRepository repo;
    private final TestEventRepository repoTestEvent;
    private final TestReportEntryRepository repoTestReportEntry;

    public TestReportService(TestReportRepository repo, TestEventRepository repoTestEvent, TestReportEntryRepository repoTestReportEntry) {
        this.repo = repo;
        this.repoTestEvent = repoTestEvent;
        this.repoTestReportEntry = repoTestReportEntry;
    }

    public List<TestReport> getAll(){
        return repo.findAll();
    }

    public List<TestReportEntry> getEntriesByTestReportId(String testReportId) {
        var entries = repoTestReportEntry.findByTestReportId(testReportId);
        return entries;
    }





}
