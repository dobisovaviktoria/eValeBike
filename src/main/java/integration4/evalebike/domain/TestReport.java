package integration4.evalebike.domain;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "test_reports")
public class TestReport {

    @Id
    private String id;

    @Column(name = "expiry_date")
    private String expiryDate;

    private String state;

    private String type;

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Column(name = "max_support")
    private double maxSupport;

    @Column(name = "engine_power_max")
    private double enginePowerMax;

    @Column(name = "engine_power_nominal")
    private double enginePowerNominal;

    @Column(name = "engine_torque")
    private double engineTorque;

    @OneToMany(mappedBy = "testReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestReportEntry> reportEntries;

    // Default constructor for JPA
    public TestReport() {}

    // Constructor for mapping from DTO
    public TestReport(String id, String expiryDate, String state, String type, double batteryCapacity,
                      double maxSupport, double enginePowerMax, double enginePowerNominal, double engineTorque,
                      List<TestReportEntry> reportEntries) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.state = state;
        this.type = type;
        this.batteryCapacity = batteryCapacity;
        this.maxSupport = maxSupport;
        this.enginePowerMax = enginePowerMax;
        this.enginePowerNominal = enginePowerNominal;
        this.engineTorque = engineTorque;
        this.reportEntries = reportEntries;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(double batteryCapacity) { this.batteryCapacity = batteryCapacity; }
    public double getMaxSupport() { return maxSupport; }
    public void setMaxSupport(double maxSupport) { this.maxSupport = maxSupport; }
    public double getEnginePowerMax() { return enginePowerMax; }
    public void setEnginePowerMax(double enginePowerMax) { this.enginePowerMax = enginePowerMax; }
    public double getEnginePowerNominal() { return enginePowerNominal; }
    public void setEnginePowerNominal(double enginePowerNominal) { this.enginePowerNominal = enginePowerNominal; }
    public double getEngineTorque() { return engineTorque; }
    public void setEngineTorque(double engineTorque) { this.engineTorque = engineTorque; }
    public List<TestReportEntry> getReportEntries() { return reportEntries; }
    public void setReportEntries(List<TestReportEntry> reportEntries) { this.reportEntries = reportEntries; }
}