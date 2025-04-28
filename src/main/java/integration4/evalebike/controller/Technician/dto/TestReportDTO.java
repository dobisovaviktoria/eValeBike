package integration4.evalebike.controller.technician.dto;

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
        List<TestReportEntryDTO> reportEntries,
        String bikeQR,
        String technicianName
) {}

//We do summarize for all the test entries in the service,which will result one report entry.
