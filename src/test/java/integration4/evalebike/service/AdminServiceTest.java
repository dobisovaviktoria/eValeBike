package integration4.evalebike.service;

import integration4.evalebike.domain.Administrator;
import integration4.evalebike.domain.UserStatus;
import integration4.evalebike.exception.NotFoundException;
import integration4.evalebike.repository.AdminRepository;
import integration4.evalebike.utility.PasswordUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordUtility passwordUtility;

    @InjectMocks
    private AdminService adminService;

    private Administrator admin;

    @BeforeEach
    void setUp() {
        admin = new Administrator("Alice Johnson", "alice.johnson@example.com", "E-Bike Corp");
        admin.setId(1);
        admin.setUserStatus(UserStatus.APPROVED);
    }

    @Test
    void getAllAdmins_shouldReturnApprovedAdmins() {
        // Arrange
        List<Administrator> admins = Arrays.asList(admin, new Administrator("Bob Brown", "bob.brown@example.com", "E-Nursing"));
        when(adminRepository.findByUserStatus(UserStatus.APPROVED)).thenReturn(admins);

        // Act
        List<Administrator> result = adminService.getAllAdmins();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Alice Johnson", result.get(0).getName());
        verify(adminRepository, times(1)).findByUserStatus(UserStatus.APPROVED);
    }

    @Test
    void getAllAdmins_shouldReturnEmptyListWhenNoApprovedAdmins() {
        // Arrange
        when(adminRepository.findByUserStatus(UserStatus.APPROVED)).thenReturn(List.of());

        // Act
        List<Administrator> result = adminService.getAllAdmins();

        // Assert
        assertTrue(result.isEmpty());
        verify(adminRepository, times(1)).findByUserStatus(UserStatus.APPROVED);
    }

    @Test
    void getAdminById_shouldReturnAdminWhenFound() {
        // Arrange
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

        // Act
        Administrator result = adminService.getAdminById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Alice Johnson", result.getName());
        verify(adminRepository, times(1)).findById(1);
    }

    @Test
    void getAdminById_shouldThrowNotFoundExceptionWhenAdminNotFound() {
        // Arrange
        when(adminRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> adminService.getAdminById(1));
        assertEquals("Admin with ID 1 was not found.", exception.getMessage()); // Updated message
        verify(adminRepository, times(1)).findById(1);
    }

    @Test
    void saveAdmin_shouldCreateAndSaveAdmin() {
        // Arrange
        String name = "Nora";
        String email = "nora@example.com";
        String companyName = "E-Nursing";
        String rawPassword = "randomPass123";
        String hashedPassword = "hashedPass123";

        when(passwordUtility.generateRandomPassword(8)).thenReturn(rawPassword);
        when(passwordUtility.hashPassword(rawPassword)).thenReturn(hashedPassword);
        when(adminRepository.save(any(Administrator.class))).thenAnswer(invocation -> {
            Administrator savedAdmin = invocation.getArgument(0);
            savedAdmin.setId(2); // Simulate ID generation
            return savedAdmin;
        });

        // Act
        Administrator result = adminService.saveAdmin(name, email, companyName);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertEquals(companyName, result.getCompanyName());
        assertEquals(hashedPassword, result.getPassword());
        verify(passwordUtility, times(1)).generateRandomPassword(8);
        verify(passwordUtility, times(1)).hashPassword(rawPassword);
        verify(passwordUtility, times(1)).sendPasswordEmail(email, rawPassword);
        verify(adminRepository, times(1)).save(any(Administrator.class));
    }

    @Test
    void updateAdmin_shouldUpdateExistingAdmin() {
        // Arrange
        Administrator updatedDetails = new Administrator("Nora Updated", "nora.updated@example.com", "E-Nursing Updated");
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));
        when(adminRepository.save(any(Administrator.class))).thenReturn(admin);

        // Act
        Administrator result = adminService.updateAdmin(1, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Nora Updated", result.getName());
        assertEquals("nora.updated@example.com", result.getEmail());
        assertEquals("E-Nursing Updated", result.getCompanyName());
        verify(adminRepository, times(1)).findById(1);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    void updateAdmin_shouldThrowRuntimeExceptionWhenAdminNotFound() {
        // Arrange
        Administrator updatedDetails = new Administrator("Nora Updated", "nora.updated@example.com", "E-Nursing Updated");
        when(adminRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> adminService.updateAdmin(1, updatedDetails));
        assertEquals("Admin not found", exception.getMessage());
        verify(adminRepository, times(1)).findById(1);
        verify(adminRepository, never()).save(any());
    }

    @Test
    void deleteAdmin_shouldDeleteExistingAdmin() {
        // Arrange
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));
        doNothing().when(adminRepository).deleteById(1);

        // Act
        adminService.deleteAdmin(1);

        // Assert
        verify(adminRepository, times(1)).findById(1);
        verify(adminRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteAdmin_shouldThrowRuntimeExceptionWhenAdminNotFound() {
        // Arrange
        when(adminRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> adminService.deleteAdmin(1));
        assertEquals("Admin not found", exception.getMessage());
        verify(adminRepository, times(1)).findById(1);
        verify(adminRepository, never()).deleteById(any());
    }
}