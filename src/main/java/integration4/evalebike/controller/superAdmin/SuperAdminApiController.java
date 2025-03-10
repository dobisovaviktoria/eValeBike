package integration4.evalebike.controller.superAdmin;

import integration4.evalebike.domain.Administrator;
import integration4.evalebike.domain.SuperAdmin;
import integration4.evalebike.service.AdminService;
import integration4.evalebike.service.SuperAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/superAdmins")
public class SuperAdminApiController {
    private SuperAdminService superAdminService;
    private AdminService adminService;

    public SuperAdminApiController(AdminService adminService, SuperAdminService superAdminService) {
        this.adminService = adminService;
        this.superAdminService = superAdminService;
    }

    //  A list of administrator
    @GetMapping()
    public List<Administrator> getAllAdmin() {
        return adminService.getAllAdmins();
    }


    //   Getting admin from the superadmin's pov
    @GetMapping("/{adminId}")
    private ResponseEntity<Administrator> getAdminById(@PathVariable Integer adminId) {
        Optional<Administrator> administrator = adminService.getAdminById(adminId);
        return administrator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Administrator> createAdmin(@RequestBody Administrator administrator) {
        Administrator administrator1= adminService.saveAdmin(administrator);
        return new ResponseEntity<>(administrator1, HttpStatus.CREATED);
    }

    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer adminId) {
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//Updating admin details
    @PutMapping("/admins/{adminId}")
    public ResponseEntity<SuperAdmin> updateSuperAdmin(@PathVariable Integer adminId, @RequestBody SuperAdmin superAdminDetails) {
        SuperAdmin updateSuperAdmin= superAdminService.updateSuperAdmin(adminId, superAdminDetails);
        return ResponseEntity.ok(updateSuperAdmin);
    }
}
