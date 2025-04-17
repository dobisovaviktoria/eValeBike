package integration4.evalebike.controller.viewModel;

import integration4.evalebike.domain.TestReportEntry;

import java.util.List;

public record TestReportEntriesViewModel(List<TestReportEntryViewModel> entries) {
    public static TestReportEntriesViewModel from(List<TestReportEntry> entries) {
        return new TestReportEntriesViewModel(
                entries.stream().map(TestReportEntryViewModel::from).toList()
        );
    }
}
