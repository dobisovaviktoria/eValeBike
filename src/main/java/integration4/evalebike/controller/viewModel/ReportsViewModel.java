package integration4.evalebike.controller.viewModel;

import integration4.evalebike.domain.TestReport;

import java.util.List;

public record ReportsViewModel(List<ReportViewModel> reportViewModels) {
    public static ReportsViewModel from (final List<TestReport> reports) {
        final var reportViewModels = reports.stream().map(ReportViewModel::from).toList();
        return new ReportsViewModel(reportViewModels);
    }
}
