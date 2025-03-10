package integration4.evalebike.controller.superAdmin;

import integration4.evalebike.domain.Administrator;
import integration4.evalebike.domain.SuperAdmin;
import integration4.evalebike.service.AdminService;
import integration4.evalebike.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class SuperAdminController {
    private final SuperAdminService superAdminService;
    private final AdminService adminService;

    @Autowired
    public SuperAdminController(SuperAdminService superAdminService, AdminService adminService) {
        this.superAdminService = superAdminService;
        this.adminService = adminService;
    }

    @GetMapping("/superAdmin/admins")
    public String getAllAdmins(Model model) {
        List<SuperAdmin> superAdmins = superAdminService.getAllSuperAdmins();
        model.addAttribute("superAdmins", superAdmins);
        return "superAdmin/admin-management";
    }

    @GetMapping("/superAdmin/add")
    public String showAddAdministratorForm(Model model) {
        model.addAttribute("admin", new Administrator());
        return "superAdmin/add-admin";
    }

}






