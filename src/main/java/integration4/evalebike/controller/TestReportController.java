package integration4.evalebike.controller;

import integration4.evalebike.controller.viewModel.ReportsViewModel;
import integration4.evalebike.service.TestBenchService;
import integration4.evalebike.service.TestReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/technician")
public class TestReportController {

    private final TestBenchService testBenchService;
    private final TestReportService testReportService;

    public TestReportController(TestBenchService testBenchService, TestReportService testReportService) {
        this.testBenchService = testBenchService;
        this.testReportService = testReportService;
    }

    @GetMapping("/report")
    public Mono<String> showReport(@RequestParam("testId") String testId, Model model) {
        return testBenchService.getTestReportById(testId)
                .map(report -> {
                    model.addAttribute("report", report);
                    return "technician/report";
                })
                .onErrorResume(e -> {
                    model.addAttribute("error", e.getMessage());
                    return Mono.just("technician/bike-dashboard");
                });
    }

    //this shows a list of test results

    @GetMapping("/test-report-dashboard")
    public ModelAndView showReportDashboard(Model model) {
        final ModelAndView modelAndView = new ModelAndView("technician/test-report-dashboard");
        modelAndView.addObject("reports", ReportsViewModel.from(testReportService.getAll()));
        return modelAndView;
    }
}