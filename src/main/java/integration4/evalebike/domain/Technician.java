package integration4.evalebike.domain;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Technician extends User {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_bench_id")
    private TestBench assignedTestBench;

    public Technician(Integer id, String name, String email, String password, TestBench testBench) {
        super(id, name, email, password);
        this.assignedTestBench = testBench;
    }

    public Technician() {

    }

    public TestBench getAssignedTestBench() {
        return assignedTestBench;
    }

    public void setAssignedTestBench(TestBench assignedTestBench) {
        this.assignedTestBench = assignedTestBench;
    }
}
