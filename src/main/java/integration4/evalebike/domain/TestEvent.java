package integration4.evalebike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//this class is just to have connection between bike and test.
//I didnt want to save these together with test report,
// as it would invoke error for DTOs, at least I assumed.
@Entity
public class TestEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bikeQR;
    private String testId;
    private String technicianUsername;

    public TestEvent(String bikeQR, String testid, String technicianUsername) {
        this.bikeQR = bikeQR;
        this.testId = testid;
        this.technicianUsername = technicianUsername;
    }

    public TestEvent() {
    }

    public String getBikeQR() {
        return bikeQR;
    }

    public void setBikeQR(String bikeQR) {
        this.bikeQR = bikeQR;
    }

    public String getTechnicianUsername() {
        return technicianUsername;
    }

    public void setTechnicianUsername(String technicianUsername) {
        this.technicianUsername = technicianUsername;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "TestEvent{" +
                "id=" + id +
                ", bikeQR='" + bikeQR + '\'' +
                ", testId='" + testId + '\'' +
                ", technicianUsername='" + technicianUsername + '\'' +
                '}';
    }
}
