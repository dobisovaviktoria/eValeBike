
package integration4.evalebike.service;

import integration4.evalebike.controller.technician.dto.TestReportDTO;
import integration4.evalebike.controller.technician.dto.TestReportEntryDTO;
import integration4.evalebike.controller.technician.dto.TestRequestDTO;
import integration4.evalebike.controller.technician.dto.TestResponseDTO;
import integration4.evalebike.domain.TestReport;
import integration4.evalebike.domain.TestReportEntry;
import integration4.evalebike.repository.TestReportRepository;
import io.netty.handler.timeout.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatusCode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TestBenchService {
    private static final Logger logger = LoggerFactory.getLogger(TestBenchService.class);

    private final WebClient testBenchClient;
    private final TestReportRepository testReportRepository;
    private final String apiKey = "9e8dffd7-f6e1-45b4-b4aa-69fd257ca200";

    public TestBenchService(WebClient.Builder webClientBuilder, TestReportRepository testReportRepository) {
        this.testBenchClient = webClientBuilder
                .baseUrl("https://testbench.raoul.dev")
                .defaultHeader("X-Api-Key", apiKey)
                .build();
        this.testReportRepository = testReportRepository;
    }

    public Mono<TestResponseDTO> processTest(TestRequestDTO request, String technicianUsername, String bikeQR) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type", request.getTestType());
        requestBody.put("batteryCapacity", request.getBatteryCapacity());
        requestBody.put("maxSupport", request.getMaxSupport());
        requestBody.put("enginePowerMax", request.getEnginePowerMax());
        requestBody.put("enginePowerNominal", request.getEnginePowerNominal());
        requestBody.put("engineTorque", request.getEngineTorque());

        logger.info("Sending request body: {}", requestBody);

        return testBenchClient.post()
                .uri("/api/test")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .doOnNext(errorBody -> logger.error("Error response: {}", errorBody))
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Failed to start test: " + errorBody))))
                .bodyToMono(TestResponseDTO.class)
                .doOnNext(response -> {
                    logger.info("Started test with ID: {}", response.getId());
                    pollTestStatus(response.getId())
                            .filter(status -> "COMPLETED".equalsIgnoreCase(status.getState()))
                            .next()
                            .flatMap(status -> getTestReportById(response.getId()))
                            .doOnNext(report -> logger.info("Fetched and saved report for testId: {}", response.getId()))
                            .subscribe(
                                    result -> logger.info("Background task completed: {}", result.id()),
                                    error -> logger.error("Background task failed: {}", error.getMessage())
                            );
                });
    }

    public Flux<TestResponseDTO> pollTestStatus(String testId) {
        return Mono.defer(() -> testBenchClient.get()
                        .uri("/api/test/{id}", testId)
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, response ->
                                response.bodyToMono(String.class)
                                        .flatMap(errorBody -> Mono.error(new RuntimeException("Failed to fetch test status: " + errorBody))))
                        .bodyToMono(TestResponseDTO.class))
                .doOnNext(status -> logger.info("Test {} status: {}", testId, status.getState()))
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(5)))
                .takeUntil(status -> "COMPLETED".equalsIgnoreCase(status.getState()))
                .timeout(Duration.ofMinutes(30))
                .onErrorResume(TimeoutException.class, e -> Mono.error(new RuntimeException("Test timed out")));
    }
//this is to check the status of the test
    public Mono<TestResponseDTO> getTestStatusById(String testId) {
        return testBenchClient.get()
                .uri("/api/test/{id}", testId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .doOnNext(errorBody -> logger.error("Failed to fetch test result for testId {}: {}", testId, errorBody))
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Failed to fetch test result: " + errorBody))))
                .bodyToMono(TestResponseDTO.class)
                .doOnNext(response -> logger.info("Fetched test result for testId {}: ID = {}", testId, response.getId()))
                .doOnError(e -> logger.error("Error fetching test result for testId {}: {}", testId, e.getMessage()));
    }


    //to get the whole test report (including test entries)

    public Mono<TestReportDTO> getTestReportById(String testId) {
        logger.info("Fetching report for testId: {}", testId);

        return Mono.fromCallable(() -> testReportRepository.findById(testId))
                .flatMap(optionalReport -> {
                    if (optionalReport.isEmpty()) {
                        logger.error("No partial TestReport found for testId: {}", testId);
                        return Mono.error(new RuntimeException("No partial TestReport found for testId: " + testId));
                    }
                    TestReport partialReport = optionalReport.get();
                    String bikeQR = partialReport.getBikeQR();
                    String technicianUsername = partialReport.getTechnicianName();
                    return fetchCsvReport(testId)
                            .flatMap(csv -> parseCsvToEntries(csv, testId))
                            .flatMap(entries -> {
                                if (entries.isEmpty()) {
                                    logger.warn("No entries parsed for testId: {}", testId);
                                    return Mono.error(new RuntimeException("No entries found for testId: " + testId));
                                }
                                return fetchAndValidateTest(testId)
                                        .flatMap(test -> saveTestReport(test, entries, bikeQR, technicianUsername))
                                        .map(testReportDTO -> {
                                            TestReportEntryDTO summarizedEntry = summarizeEntries(entries);
                                            return new TestReportDTO(
                                                    testReportDTO.id(),
                                                    testReportDTO.expiryDate(),
                                                    testReportDTO.state(),
                                                    testReportDTO.type(),
                                                    testReportDTO.batteryCapacity(),
                                                    testReportDTO.maxSupport(),
                                                    testReportDTO.enginePowerMax(),
                                                    testReportDTO.enginePowerNominal(),
                                                    testReportDTO.engineTorque(),
                                                    List.of(summarizedEntry), // Return only summarized entry
                                                    testReportDTO.bikeQR(),
                                                    testReportDTO.technicianName()
                                            );
                                        });
                            });
                })
                .doOnError(e -> logger.error("Error fetching report for testId {}: {}", testId, e.getMessage()));
    }



    public Mono<String> fetchCsvReport(String testId) {
        return testBenchClient.get()
                .uri("/api/test/{id}/report", testId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .doOnNext(body -> logger.error("Report fetch error: {} - {}", response.statusCode(), body))
                                .flatMap(body -> Mono.error(new RuntimeException("Failed to fetch test report: " + body))))
                .bodyToMono(String.class)
                .doOnNext(csv -> logger.info("Received CSV for {}: {}", testId, csv.substring(0, Math.min(csv.length(), 100)) + "..."));
    }

    public Mono<List<TestReportEntryDTO>> parseCsvToEntries(String csvData, String testId) {
        try {
            List<TestReportEntryDTO> entries = parseCsvToReportEntries(csvData);
            logger.info("Parsed {} report entries for testId {}", entries.size(), testId);
            return Mono.just(entries);
        } catch (Exception e) {
            logger.error("CSV parsing failed for {}: {}", testId, e.getMessage());
            return Mono.error(new RuntimeException("Failed to parse CSV", e));
        }
    }

    public Mono<TestResponseDTO> fetchAndValidateTest(String testId) {
        return getTestStatusById(testId)
                .flatMap(response -> {
                    if (response.getId() == null) {
                        logger.error("Null test ID for {}", testId);
                        return Mono.error(new RuntimeException("Test ID is null"));
                    }
                    if (!"COMPLETED".equalsIgnoreCase(response.getState())) {
                        logger.warn("Test not completed for {}: state={}", testId, response.getState());
                        return Mono.error(new RuntimeException("Test is not completed yet"));
                    }
                    return Mono.just(response);
                });
    }

    public Mono<TestReportDTO> saveTestReport(TestResponseDTO test, List<TestReportEntryDTO> entryDTOs, String bikeQR, String technicianUsername) {
        TestReport testReport = new TestReport(
                test.getId(),
                test.getExpiryDate(),
                test.getState(),
                test.getType(),
                test.getBatteryCapacity(),
                test.getMaxSupport(),
                test.getEnginePowerMax(),
                test.getEnginePowerNominal(),
                test.getEngineTorque(),
                null
        );
        testReport.setBikeQR(bikeQR);
        testReport.setTechnicianName(technicianUsername);

        List<TestReportEntry> entries = entryDTOs.stream()
                .map(dto -> new TestReportEntry(
                        testReport,
                        dto.timestamp(),
                        dto.batteryVoltage(),
                        dto.batteryCurrent(),
                        dto.batteryCapacity(),
                        dto.batteryTemperatureCelsius(),
                        dto.chargeStatus(),
                        dto.assistanceLevel(),
                        dto.torqueCrankNm(),
                        dto.bikeWheelSpeedKmh(),
                        dto.cadanceRpm(),
                        dto.engineRpm(),
                        dto.enginePowerWatt(),
                        dto.wheelPowerWatt(),
                        dto.rollTorque(),
                        dto.loadcellN(),
                        dto.rolHz(),
                        dto.horizontalInclination(),
                        dto.verticalInclination(),
                        dto.loadPower(),
                        dto.statusPlug()
                ))
                .collect(Collectors.toList());

        testReport.setReportEntries(entries);

        try {
            TestReport saved = testReportRepository.save(testReport);
            logger.info("Saved TestReport with ID: {} for testId {}", saved.getId(), test.getId());
            return Mono.just(new TestReportDTO(
                    test.getId(),
                    test.getExpiryDate(),
                    test.getState(),
                    test.getType(),
                    test.getBatteryCapacity(),
                    test.getMaxSupport(),
                    test.getEnginePowerMax(),
                    test.getEnginePowerNominal(),
                    test.getEngineTorque(),
                    entryDTOs,
                    saved.getBikeQR(),
                    saved.getTechnicianName()
            ));
        } catch (Exception e) {
            logger.error("Failed to save report for testId {}: {}", test.getId(), e.getMessage());
            return Mono.error(new RuntimeException("Failed to save TestReport", e));
        }
    }

    public List<TestReportEntryDTO> parseCsvToReportEntries(String csvData) {
        String[] lines = csvData.trim().split("\n");
        return java.util.Arrays.stream(lines)
                .skip(1)
                .map(line -> {
                    String[] values = line.split(",");
                    try {
                        return new TestReportEntryDTO(
                                LocalDateTime.parse(values[0]),
                                Double.parseDouble(values[1]),
                                Double.parseDouble(values[2]),
                                Double.parseDouble(values[3]),
                                Double.parseDouble(values[4]),
                                Integer.parseInt(values[5]),
                                Integer.parseInt(values[6]),
                                Double.parseDouble(values[7]),
                                Double.parseDouble(values[8]),
                                Integer.parseInt(values[9]),
                                Integer.parseInt(values[10]),
                                Double.parseDouble(values[11]),
                                Double.parseDouble(values[12]),
                                Double.parseDouble(values[13]),
                                Double.parseDouble(values[14]),
                                Double.parseDouble(values[15]),
                                Double.parseDouble(values[16]),
                                Double.parseDouble(values[17]),
                                Double.parseDouble(values[18]),
                                Boolean.parseBoolean(values[19])
                        );
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse CSV line: " + line, e);
                    }
                })
                .collect(Collectors.toList());
    }

    public TestReportEntryDTO summarizeEntries(List<TestReportEntryDTO> entries) {
        if (entries == null || entries.isEmpty()) {
            throw new IllegalArgumentException("Entries list is empty or null");
        }

        int count = entries.size();

        double avgBatteryVoltage = round(entries.stream().mapToDouble(TestReportEntryDTO::batteryVoltage).average().orElse(0));
        double avgBatteryCurrent = round(entries.stream().mapToDouble(TestReportEntryDTO::batteryCurrent).average().orElse(0));
        double avgBatteryCapacity = round(entries.stream().mapToDouble(TestReportEntryDTO::batteryCapacity).average().orElse(0));
        double avgBatteryTemp = round(entries.stream().mapToDouble(TestReportEntryDTO::batteryTemperatureCelsius).average().orElse(0));
        int avgChargeStatus = (int) Math.round(entries.stream().mapToInt(TestReportEntryDTO::chargeStatus).average().orElse(0));
        int avgAssistanceLevel = (int) Math.round(entries.stream().mapToInt(TestReportEntryDTO::assistanceLevel).average().orElse(0));
        double avgTorqueCrank = round(entries.stream().mapToDouble(TestReportEntryDTO::torqueCrankNm).average().orElse(0));
        double avgBikeWheelSpeed = round(entries.stream().mapToDouble(TestReportEntryDTO::bikeWheelSpeedKmh).average().orElse(0));
        int avgCadenceRpm = (int) Math.round(entries.stream().mapToInt(TestReportEntryDTO::cadanceRpm).average().orElse(0));
        int avgEngineRpm = (int) Math.round(entries.stream().mapToInt(TestReportEntryDTO::engineRpm).average().orElse(0));
        double avgEnginePowerWatt = round(entries.stream().mapToDouble(TestReportEntryDTO::enginePowerWatt).average().orElse(0));
        double avgWheelPowerWatt = round(entries.stream().mapToDouble(TestReportEntryDTO::wheelPowerWatt).average().orElse(0));
        double avgRollTorque = round(entries.stream().mapToDouble(TestReportEntryDTO::rollTorque).average().orElse(0));
        double avgLoadcellN = round(entries.stream().mapToDouble(TestReportEntryDTO::loadcellN).average().orElse(0));
        double avgRolHz = round(entries.stream().mapToDouble(TestReportEntryDTO::rolHz).average().orElse(0));
        double avgHorizontalInclination = round(entries.stream().mapToDouble(TestReportEntryDTO::horizontalInclination).average().orElse(0));
        double avgVerticalInclination = round(entries.stream().mapToDouble(TestReportEntryDTO::verticalInclination).average().orElse(0));
        double avgLoadPower = round(entries.stream().mapToDouble(TestReportEntryDTO::loadPower).average().orElse(0));

        boolean statusPlug = entries.stream().map(TestReportEntryDTO::statusPlug)
                .filter(Boolean::booleanValue)
                .count() > count / 2;  // majority wins

        LocalDateTime timestamp = entries.get(0).timestamp();

        return new TestReportEntryDTO(
                timestamp,
                avgBatteryVoltage,
                avgBatteryCurrent,
                avgBatteryCapacity,
                avgBatteryTemp,
                avgChargeStatus,
                avgAssistanceLevel,
                avgTorqueCrank,
                avgBikeWheelSpeed,
                avgCadenceRpm,
                avgEngineRpm,
                avgEnginePowerWatt,
                avgWheelPowerWatt,
                avgRollTorque,
                avgLoadcellN,
                avgRolHz,
                avgHorizontalInclination,
                avgVerticalInclination,
                avgLoadPower,
                statusPlug
        );
    }

    // Round to 2 decimal places
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}