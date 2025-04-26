package integration4.evalebike.controller.testBench.dto;

import java.time.LocalDateTime;

import java.util.List;

public record TestReportDTO(
        String id,
        String expiryDate,
        String state,
        String type,
        double batteryCapacity,
        double maxSupport,
        double enginePowerMax,
        double enginePowerNominal,
        double engineTorque,
        List<TestReportEntryDTO> reportEntries
) {}