//package integration4.evalebike.service;
//
//import integration4.evalebike.domain.Administrator;
//import integration4.evalebike.domain.UserStatus;
//import integration4.evalebike.exception.NotFoundException;
//import integration4.evalebike.repository.AdminRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@Transactional // Roll back database changes after each test
//public class AdminServiceTest {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Autowired
//    private AdminService adminService;
//
//    private Administrator admin;
//
//    @BeforeEach
//    public void setUp() {
//        // Use one of the existing admins from testdata.sql
//        admin = new Administrator();
//        admin.setId(3); // Matches Alice Johnson from testdata.sql
//        admin.setName("Alice Johnson");
//        admin.setEmail("alice.johnson@example.com");
//        admin.setCompanyName("E-Bike Corp");
//        admin.setUserStatus(UserStatus.APPROVED);
//    }
//
//    @Test
//    public void testGetAllAdmins() {
//        List<Administrator> result = adminService.getAllAdmins();
//
//        assertNotNull(result);
//        // testdata.sql has 2 admins (ID 3 and 16)
//        assertEquals(2, result.size());
//        // Check one of the admins
//        Optional<Administrator> alice = result.stream()
//                .filter(a -> a.getId() == 3)
//                .findFirst();
//        assertTrue(alice.isPresent());
//        assertEquals("Alice Johnson", alice.get().getName());
//    }
//
//    @Test
//    public void testGetAdminById_Found() {
//        Administrator result = adminService.getAdminById(3); // ID 3 exists in testdata.sql
//
//        assertNotNull(result);
//        assertEquals("Alice Johnson", result.getName());
//        assertEquals("alice.johnson@example.com", result.getEmail());
//    }
//
//    @Test
//    public void testGetAdminById_NotFound() {
//        assertThrows(NotFoundException.class, () -> adminService.getAdminById(999)); // ID 999 does not exist
//    }
//
//    @Test
//    public void testSaveAdmin() {
//        // Save a new admin
//        Administrator result = adminService.saveAdmin("New Admin", "newadmin@example.com", "New Co.");
//
//        assertNotNull(result);
//        assertEquals("New Admin", result.getName());
//        assertEquals("newadmin@example.com", result.getEmail());
//        assertEquals("New Co.", result.getCompanyName());
//        assertNotNull(result.getPassword()); // Password should be hashed by PasswordUtility
//        assertEquals(UserStatus.APPROVED, result.getUserStatus());
//
//        // Verify the admin was saved to the database
//        Optional<Administrator> savedAdmin = adminRepository.findById(result.getId());
//        assertTrue(savedAdmin.isPresent());
//        assertEquals("newadmin@example.com", savedAdmin.get().getEmail());
//    }
//
//    @Test
//    public void testUpdateAdmin_Found() {
//        Administrator updatedAdmin = new Administrator();
//        updatedAdmin.setName("Updated Alice");
//        updatedAdmin.setEmail("updated.alice@example.com");
//        updatedAdmin.setCompanyName("Updated Co.");
//
//        Administrator result = adminService.updateAdmin(3, updatedAdmin); // ID 3 exists
//
//        assertNotNull(result);
//        assertEquals("Updated Alice", result.getName());
//        assertEquals("updated.alice@example.com", result.getEmail());
//        assertEquals("Updated Co.", result.getCompanyName());
//
//        // Verify the update in the database
//        Optional<Administrator> dbAdmin = adminRepository.findById(3);
//        assertTrue(dbAdmin.isPresent());
//        assertEquals("Updated Alice", dbAdmin.get().getName());
//    }
//
//    @Test
//    public void testUpdateAdmin_NotFound() {
//        Administrator updatedAdmin = new Administrator();
//        updatedAdmin.setName("Jane");
//
//        assertThrows(NotFoundException.class, () -> adminService.updateAdmin(999, updatedAdmin)); // ID 999 does not exist
//    }
//
//    @Test
//    public void testDeleteAdmin_Found() {
//        adminService.deleteAdmin(3); // ID 3 exists
//
//        // Verify the admin was deleted
//        Optional<Administrator> deletedAdmin = adminRepository.findById(3);
//        assertFalse(deletedAdmin.isPresent());
//    }
//
//    @Test
//    public void testDeleteAdmin_NotFound() {
//        assertThrows(NotFoundException.class, () -> adminService.deleteAdmin(999)); // ID 999 does not exist
//    }
//}