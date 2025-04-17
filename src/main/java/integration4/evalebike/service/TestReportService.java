package integration4.evalebike.service;

import integration4.evalebike.domain.TestReport;
import integration4.evalebike.repository.TestReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestReportService {
    private final TestReportRepository repo;

    public TestReportService(TestReportRepository repo) {
        this.repo = repo;
    }

    public List<TestReport> getAll(){
        return repo.findAll();
    }



}
