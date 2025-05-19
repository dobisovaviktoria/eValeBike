package integration4.evalebike.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;

    public Company(String companyName, String companyAddress, String companyEmail, String companyPhone) {
        this.name = companyName;
        this.address = companyAddress;
        this.email = companyEmail;
        this.phone = companyPhone;
    }

    public Company() {
    }
}
