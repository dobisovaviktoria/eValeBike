package integration4.evalebike.service;

import integration4.evalebike.domain.SuperAdmin;
import integration4.evalebike.repository.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperAdminService {

    @Autowired
    private SuperAdminRepository superAdminRepository;

    // Get all admins
    public List<SuperAdmin> getAllSuperAdmins() {
        return superAdminRepository.findAll();
    }

    // Get admin by ID
    public Optional<SuperAdmin> getSuperAdminById(Integer id) {
        return superAdminRepository.findById(id);
    }

    // Save a new admin
    public SuperAdmin saveSuperAdmin(SuperAdmin superAdminadmin) {
        return superAdminRepository.save(superAdminadmin);
    }

    // Update a superadmin
    public SuperAdmin updateSuperAdmin(Integer id, SuperAdmin adminDetails) {
        SuperAdmin existingSuperAdmin = superAdminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        existingSuperAdmin.setName(adminDetails.getName());
        existingSuperAdmin.setEmail(adminDetails.getEmail());
        existingSuperAdmin.setCompanyName(adminDetails.getCompanyName());
        return superAdminRepository.save(existingSuperAdmin);
    }


}
