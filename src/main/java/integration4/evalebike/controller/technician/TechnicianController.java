package integration4.evalebike.controller.technician;

import integration4.evalebike.controller.technician.dto.BikeDto;
import integration4.evalebike.controller.technician.dto.TestResponseDTO;
import integration4.evalebike.controller.viewModel.ReportViewModel;
import integration4.evalebike.controller.viewModel.ReportsViewModel;
import integration4.evalebike.controller.viewModel.TestReportEntryViewModel;
import integration4.evalebike.domain.Bike;
import integration4.evalebike.domain.BikeOwner;
import integration4.evalebike.domain.TestReport;
import integration4.evalebike.domain.TestReportEntry;
import integration4.evalebike.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/technician")
public class TechnicianController {
    private final BikeOwnerService bikeOwnerService;
    private final BikeService bikeService;
    private final QrCodeService qrCodeService;
    private final TestBenchService testBenchService;
    private final TestReportService testReportService;

    public TechnicianController(BikeOwnerService bikeOwnerService, BikeService bikeService, QrCodeService qrCodeService, TestBenchService testBenchService, TestReportService testReportService) {
        this.bikeOwnerService = bikeOwnerService;
        this.bikeService = bikeService;
        this.qrCodeService = qrCodeService;
        this.testBenchService = testBenchService;
        this.testReportService = testReportService;
    }

    // Show all bikes owned by a specific bike owner
    @GetMapping("/bikes/owner/{id}")
    public String showBikesForOwner(@PathVariable("id") Integer ownerId, Model model) {
        List<BikeDto> bikeOwnerBikes = bikeOwnerService.getAllBikeOfOwner(ownerId);
        model.addAttribute("bikes", bikeOwnerBikes);
        return "technician/bike-dashboard";
    }

    @GetMapping("/bikes")
    public String logBikes(Model model) {
        List<Bike> bikes = bikeService.getBikes();
        for (Bike bike : bikes) {
            String qrCodeImage = qrCodeService.generateQrCodeBase64(bike.getBikeQR(), 200, 200);
            bike.setQrCodeImage(qrCodeImage);
        }
        model.addAttribute("bikes", bikes);
        return "technician/bike-dashboard";
    }

    @GetMapping("/bike-owners")
    public String logBikeOwners(Model model) {
        List<BikeOwner> bikeOwners = bikeOwnerService.getAllBikeOwners();
        model.addAttribute("bikeOwners", bikeOwners);
        return "technician/bike-owner-dashboard";
    }

    @GetMapping("/bikes/add")
    public String showAddBikeForm(Model model) {
        model.addAttribute("bike", new Bike());
        return "technician/add-bike";
    }

    @GetMapping("/bike-owners/add")
    public String showAddBikeOwnerForm(Model model) {
        model.addAttribute("bikeOwner", new BikeOwner());
        return "technician/add-bike-owner";
    }

    @PostMapping("/bikes/add")
    public String addBike() {
        return "technician/add-bike";
    }

    @PostMapping("/bike-owners/add")
    public String addBikeOwner() {
        return "technician/add-bike-owner";
    }

    // Show details for a specific bike
    @GetMapping("/bikes/{bikeQR}")
    public String showBikeDetails(@PathVariable String bikeQR, Model model) {
        Bike bike = bikeService.getBikeByQR(bikeQR);
        model.addAttribute("bike", bike);
        return "technician/bike-dashboard";
    }

    @GetMapping("bikes/test-types/{bikeQR}")
    public String testTypes(@PathVariable String bikeQR, Model model) {
        Bike bike = bikeService.getBikeByQR(bikeQR);
        model.addAttribute("bike", bike);
        return "technician/test-types";
    }

    @GetMapping("/loading")
    public String loading(@RequestParam("testId") String testId, Model model) {
        model.addAttribute("testId", testId);
        return "technician/loading";
    }

    @GetMapping("/test-status/{testId}")
    public Mono<TestResponseDTO> getTestResultById(@PathVariable String testId) {
        return testBenchService.getTestStatusById(testId);
    }

    @GetMapping("/report/{testId}")
    public Mono<String> showReportByTestId(@PathVariable("testId") String testId, Model model) {
        TestReport report = testReportService.getTestReportById(testId);
        ReportViewModel reportVm = ReportViewModel.from(report);

        List<TestReportEntry> entries = report.getReportEntries();
        TestReportEntryViewModel summaryVm = entries != null && !entries.isEmpty()
                ? TestReportEntryViewModel.summarize(entries)
                : null;

        model.addAttribute("report", reportVm);
        model.addAttribute("summary", summaryVm);

        return Mono.just("technician/test-report-details");
    }


    //this shows a list of test report
    @GetMapping("/test-report-dashboard")
    public ModelAndView showReportDashboard(Model model) {
        final ModelAndView modelAndView = new ModelAndView("technician/test-report-dashboard");
        modelAndView.addObject("reports", ReportsViewModel.from(testReportService.getAllReports()));
        return modelAndView;
    }
}









