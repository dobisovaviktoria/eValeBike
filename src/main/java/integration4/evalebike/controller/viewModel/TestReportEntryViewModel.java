package integration4.evalebike.controller.viewModel;


import integration4.evalebike.domain.TestReportEntry;

import java.time.LocalDateTime;

public record TestReportEntryViewModel(
        LocalDateTime timestamp,
        double batteryVoltage,
        double batteryCurrent,
        double batteryCapacity,
        double batteryTemperatureCelsius,
        int chargeStatus,
        int assistanceLevel,
        double torqueCrankNm,
        double bikeWheelSpeedKmh,
        int cadanceRpm,
        int engineRpm,
        double enginePowerWatt,
        double wheelPowerWatt,
        double rollTorque,
        double loadcellN,
        double rolHz,
        double horizontalInclination,
        double verticalInclination,
        double loadPower,
        boolean statusPlug
) {
    public static TestReportEntryViewModel from(TestReportEntry entry) {
        return new TestReportEntryViewModel(
                entry.getTimestamp(),
                entry.getBatteryVoltage(),
                entry.getBatteryCurrent(),
                entry.getBatteryCapacity(),
                entry.getBatteryTemperatureCelsius(),
                entry.getChargeStatus(),
                entry.getAssistanceLevel(),
                entry.getTorqueCrankNm(),
                entry.getBikeWheelSpeedKmh(),
                entry.getCadanceRpm(),
                entry.getEngineRpm(),
                entry.getEnginePowerWatt(),
                entry.getWheelPowerWatt(),
                entry.getRollTorque(),
                entry.getLoadcellN(),
                entry.getRolHz(),
                entry.getHorizontalInclination(),
                entry.getVerticalInclination(),
                entry.getLoadPower(),
                entry.isStatusPlug()
        );
    }
}

