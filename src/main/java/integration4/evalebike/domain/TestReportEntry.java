package integration4.evalebike.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_report_entries")
public class TestReportEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_report_id", nullable = false)
    private TestReport testReport;

    private LocalDateTime timestamp;

    @Column(name = "battery_voltage")
    private double batteryVoltage;

    @Column(name = "battery_current")
    private double batteryCurrent;

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Column(name = "battery_temperature_celsius")
    private double batteryTemperatureCelsius;

    @Column(name = "charge_status")
    private int chargeStatus;

    @Column(name = "assistance_level")
    private int assistanceLevel;

    @Column(name = "torque_crank_nm")
    private double torqueCrankNm;

    @Column(name = "bike_wheel_speed_kmh")
    private double bikeWheelSpeedKmh;

    @Column(name = "cadance_rpm")
    private int cadanceRpm;

    @Column(name = "engine_rpm")
    private int engineRpm;

    @Column(name = "engine_power_watt")
    private double enginePowerWatt;

    @Column(name = "wheel_power_watt")
    private double wheelPowerWatt;

    @Column(name = "roll_torque")
    private double rollTorque;

    @Column(name = "loadcell_n")
    private double loadcellN;

    @Column(name = "rol_hz")
    private double rolHz;

    @Column(name = "horizontal_inclination")
    private double horizontalInclination;

    @Column(name = "vertical_inclination")
    private double verticalInclination;

    @Column(name = "load_power")
    private double loadPower;

    @Column(name = "status_plug")
    private boolean statusPlug;

    // Default constructor for JPA
    public TestReportEntry() {}

    // Constructor for mapping from DTO
    public TestReportEntry(TestReport testReport, LocalDateTime timestamp, double batteryVoltage, double batteryCurrent,
                           double batteryCapacity, double batteryTemperatureCelsius, int chargeStatus, int assistanceLevel,
                           double torqueCrankNm, double bikeWheelSpeedKmh, int cadanceRpm, int engineRpm,
                           double enginePowerWatt, double wheelPowerWatt, double rollTorque, double loadcellN,
                           double rolHz, double horizontalInclination, double verticalInclination, double loadPower,
                           boolean statusPlug) {
        this.testReport = testReport;
        this.timestamp = timestamp;
        this.batteryVoltage = batteryVoltage;
        this.batteryCurrent = batteryCurrent;
        this.batteryCapacity = batteryCapacity;
        this.batteryTemperatureCelsius = batteryTemperatureCelsius;
        this.chargeStatus = chargeStatus;
        this.assistanceLevel = assistanceLevel;
        this.torqueCrankNm = torqueCrankNm;
        this.bikeWheelSpeedKmh = bikeWheelSpeedKmh;
        this.cadanceRpm = cadanceRpm;
        this.engineRpm = engineRpm;
        this.enginePowerWatt = enginePowerWatt;
        this.wheelPowerWatt = wheelPowerWatt;
        this.rollTorque = rollTorque;
        this.loadcellN = loadcellN;
        this.rolHz = rolHz;
        this.horizontalInclination = horizontalInclination;
        this.verticalInclination = verticalInclination;
        this.loadPower = loadPower;
        this.statusPlug = statusPlug;
    }

    // Getters and setters
    public Long getEntryId() { return entryId; }
    public void setEntryId(Long entryId) { this.entryId = entryId; }
    public TestReport getTestReport() { return testReport; }
    public void setTestReport(TestReport testReport) { this.testReport = testReport; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public double getBatteryVoltage() { return batteryVoltage; }
    public void setBatteryVoltage(double batteryVoltage) { this.batteryVoltage = batteryVoltage; }
    public double getBatteryCurrent() { return batteryCurrent; }
    public void setBatteryCurrent(double batteryCurrent) { this.batteryCurrent = batteryCurrent; }
    public double getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(double batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    public double getBatteryTemperatureCelsius() { return batteryTemperatureCelsius; }
    public void setBatteryTemperatureCelsius(double batteryTemperatureCelsius) { this.batteryTemperatureCelsius = batteryTemperatureCelsius; }
    public int getChargeStatus() { return chargeStatus; }
    public void setChargeStatus(int chargeStatus) { this.chargeStatus = chargeStatus; }
    public int getAssistanceLevel() { return assistanceLevel; }
    public void setAssistanceLevel(int assistanceLevel) { this.assistanceLevel = assistanceLevel; }
    public double getTorqueCrankNm() { return torqueCrankNm; }
    public void setTorqueCrankNm(double torqueCrankNm) { this.torqueCrankNm = torqueCrankNm; }
    public double getBikeWheelSpeedKmh() { return bikeWheelSpeedKmh; }
    public void setBikeWheelSpeedKmh(double bikeWheelSpeedKmh) { this.bikeWheelSpeedKmh = bikeWheelSpeedKmh; }
    public int getCadanceRpm() { return cadanceRpm; }
    public void setCadanceRpm(int cadanceRpm) { this.cadanceRpm = cadanceRpm; }
    public int getEngineRpm() { return engineRpm; }
    public void setEngineRpm(int engineRpm) { this.engineRpm = engineRpm; }
    public double getEnginePowerWatt() { return enginePowerWatt; }
    public void setEnginePowerWatt(double enginePowerWatt) { this.enginePowerWatt = enginePowerWatt; }
    public double getWheelPowerWatt() { return wheelPowerWatt; }
    public void setWheelPowerWatt(double wheelPowerWatt) { this.wheelPowerWatt = wheelPowerWatt; }
    public double getRollTorque() { return rollTorque; }
    public void setRollTorque(double rollTorque) { this.rollTorque = rollTorque; }
    public double getLoadcellN() { return loadcellN; }
    public void setLoadcellN(double loadcellN) { this.loadcellN = loadcellN; }
    public double getRolHz() { return rolHz; }
    public void setRolHz(double rolHz) { this.rolHz = rolHz; }
    public double getHorizontalInclination() { return horizontalInclination; }
    public void setHorizontalInclination(double horizontalInclination) { this.horizontalInclination = horizontalInclination; }
    public double getVerticalInclination() { return verticalInclination; }
    public void setVerticalInclination(double verticalInclination) { this.verticalInclination = verticalInclination; }
    public double getLoadPower() { return loadPower; }
    public void setLoadPower(double loadPower) { this.loadPower = loadPower; }
    public boolean isStatusPlug() { return statusPlug; }
    public void setStatusPlug(boolean statusPlug) { this.statusPlug = statusPlug; }
}
