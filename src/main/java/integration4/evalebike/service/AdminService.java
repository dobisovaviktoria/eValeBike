package integration4.evalebike.service;

import integration4.evalebike.domain.Administrator;
import integration4.evalebike.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Administrator>  getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Administrator> getAdminById(Integer id) {
        return adminRepository.findById(id);
    }

    public Administrator saveAdmin(Administrator admin) {
        return adminRepository.save(admin);

    }
    public Administrator updateAdmin(Integer id, Administrator adminDetails) {
        Administrator existingAdmin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        existingAdmin.setName(adminDetails.getName());
        existingAdmin.setEmail(adminDetails.getEmail());
//        existingAdmin.setRole(adminDetails.getRole());
        existingAdmin.setCompanyName(adminDetails.getCompanyName());
        return adminRepository.save(existingAdmin);
    }

    public void deleteAdmin(Integer id) {
        Administrator admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        adminRepository.deleteById(id);
    }

}
