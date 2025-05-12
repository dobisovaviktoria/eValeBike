package integration4.evalebike;

import integration4.evalebike.domain.*;
import integration4.evalebike.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.bouncycastle.pqc.jcajce.provider.BIKE;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("test")
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepositoryTest userRepository;
    @Autowired
    private AdminRepositoryTest administratorRepository;
    @Autowired
    private BikeOwnerRepositoryTest bikeOwnerRepository;
    @Autowired
    private BikeOwnerBikeRepositoryTest bikeOwnerBikeRepository;
    @Autowired
    private BikeRepositoryTest bikeRepository;
    @Autowired
    private RecentActivityRepositoryTest recentActivityRepository;
    @Autowired
    private SuperAdminRepositoryTest superAdminRepository;
    @Autowired
    private TechnicianRepositoryTest technicianRepository;
    @Autowired
    private TestBenchRepositoryTest testBenchRepository;
    @Autowired
    private TestReportEntryRepositoryTest testReportEntryRepository;
    @Autowired
    private TestReportRepository testReportRepository;
    @Autowired
    private VisualInspectionRepositoryTest visualInspectionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert into app_user (User)
        if (!userRepository.existsById(1)) {
            BikeOwner user1 = new BikeOwner();
            user1.setName("John Doe");
            user1.setEmail("john.doe@example.com");
            user1.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user1.setRole(Role.valueOf("BIKE_OWNER"));
            user1.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user1);
        }
        if (!userRepository.existsById(2)) {
            Technician user2 = new Technician();
            user2.setName("Jane Smith");
            user2.setEmail("jane.smith@example.com");
            user2.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user2.setRole(Role.valueOf("TECHNICIAN"));
            user2.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user2);
        }
        if (!userRepository.existsById(3)) {
            Administrator user3 = new Administrator();
            user3.setName("Alice Johnson");
            user3.setEmail("alice.johnson@example.com");
            user3.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user3.setRole(Role.valueOf("ADMIN"));
            user3.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user3);
        }
        if (!userRepository.existsById(4)) {
            SuperAdmin user4 = new SuperAdmin();
            user4.setName("Bob Brown");
            user4.setEmail("bob.brown@example.com");
            user4.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user4.setRole(Role.valueOf("SUPER_ADMIN"));
            user4.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user4);
        }
        if (!userRepository.existsById(5)) {
            BikeOwner user5 = new BikeOwner();
            user5.setName("Michael Green");
            user5.setEmail("michael.green@example.com");
            user5.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user5.setRole(Role.valueOf("BIKE_OWNER"));
            user5.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user5);
        }
        if (!userRepository.existsById(6)) {
            BikeOwner user6 = new BikeOwner();
            user6.setName("Sarah White");
            user6.setEmail("sarah.white@example.com");
            user6.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user6.setRole(Role.valueOf("BIKE_OWNER"));
            user6.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user6);
        }
        if (!userRepository.existsById(7)) {
            Technician user7 = new Technician();
            user7.setName("Nathan");
            user7.setEmail("nathan@example.com");
            user7.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user7.setRole(Role.valueOf("TECHNICIAN"));
            user7.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user7);
        }
        if (!userRepository.existsById(8)) {
            BikeOwner user8 = new BikeOwner();
            user8.setName("Nathaniel");
            user8.setEmail("nathaniel@example.com");
            user8.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user8.setRole(Role.valueOf("BIKE_OWNER"));
            user8.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user8);
        }
        if (!userRepository.existsById(9)) {
            BikeOwner user9 = new BikeOwner();
            user9.setName("Jean");
            user9.setEmail("jean@example.com");
            user9.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user9.setRole(Role.valueOf("BIKE_OWNER"));
            user9.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user9);
        }
        if (!userRepository.existsById(10)) {
            BikeOwner user10 = new BikeOwner();
            user10.setName("Jeremy");
            user10.setEmail("jeremy@example.com");
            user10.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user10.setRole(Role.valueOf("BIKE_OWNER"));
            user10.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user10);
        }
        if (!userRepository.existsById(11)) {
            BikeOwner user11 = new BikeOwner();
            user11.setName("Kevin");
            user11.setEmail("kevine@example.com");
            user11.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user11.setRole(Role.valueOf("BIKE_OWNER"));
            user11.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user11);
        }
        if (!userRepository.existsById(12)) {
            Technician user12 = new Technician();
            user12.setName("Andrew");
            user12.setEmail("andrew@example.com");
            user12.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user12.setRole(Role.valueOf("TECHNICIAN"));
            user12.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user12);
        }
        if (!userRepository.existsById(13)) {
            Technician user13 = new Technician();
            user13.setName("Nicky");
            user13.setEmail("nicky@example.com");
            user13.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user13.setRole(Role.valueOf("TECHNICIAN"));
            user13.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user13);
        }
        if (!userRepository.existsById(14)) {
            Technician user14 = new Technician();
            user14.setName("Aaron");
            user14.setEmail("aaron@example.com");
            user14.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user14.setRole(Role.valueOf("TECHNICIAN"));
            user14.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user14);
        }
        if (!userRepository.existsById(15)) {
            Technician user15 = new Technician();
            user15.setName("Riko");
            user15.setEmail("riko@example.com");
            user15.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user15.setRole(Role.valueOf("TECHNICIAN"));
            user15.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user15);
        }
        if (!userRepository.existsById(16)) {
            Administrator user16 = new Administrator();
            user16.setName("Nora");
            user16.setEmail("nora@example.com");
            user16.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user16.setRole(Role.valueOf("ADMIN"));
            user16.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user16);
        }
        if (!userRepository.existsById(17)) {
            Technician user17 = new Technician();
            user17.setName("Waymack");
            user17.setEmail("waymack@example.com");
            user17.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user17.setRole(Role.valueOf("TECHNICIAN"));
            user17.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user17);
        }
        if (!userRepository.existsById(18)) {
            BikeOwner user18 = new BikeOwner();
            user18.setName("Sara");
            user18.setEmail("sara@example.com");
            user18.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user18.setRole(Role.valueOf("BIKE_OWNER"));
            user18.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user18);
        }
        if (!userRepository.existsById(19)) {
            Technician user19 = new Technician();
            user19.setName("Abby");
            user19.setEmail("abby@example.com");
            user19.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user19.setRole(Role.valueOf("TECHNICIAN"));
            user19.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user19);
        }
        if (!userRepository.existsById(20)) {
            BikeOwner user20 = new BikeOwner();
            user20.setName("Lila");
            user20.setEmail("lila@example.com");
            user20.setPassword("$2a$10$kU6clDpBtcQAiul4xa9GP.Liy2GmP3QCPXHeZNjBSLj240YukGx7K");
            user20.setRole(Role.valueOf("BIKE_OWNER"));
            user20.setUserStatus(UserStatus.valueOf("APPROVED"));
            userRepository.save(user20);
        }

        // Insert into bike_owner
        if (!bikeOwnerRepository.existsById(1)) {
            BikeOwner bo1 = new BikeOwner();
            bo1.setPhoneNumber("555-1234");
            bo1.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo1);
        }
        if (!bikeOwnerRepository.existsById(6)) {
            BikeOwner bo6 = new BikeOwner();
            bo6.setPhoneNumber("555-7890");
            bo6.setBirthDate(LocalDate.of(1992, 7, 22));
            bikeOwnerRepository.save(bo6);
        }
        if (!bikeOwnerRepository.existsById(8)) {
            BikeOwner bo8 = new BikeOwner();
            bo8.setPhoneNumber("555-1234");
            bo8.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo8);
        }
        if (!bikeOwnerRepository.existsById(9)) {
            BikeOwner bo9 = new BikeOwner();
            bo9.setPhoneNumber("555-1234");
            bo9.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo9);
        }
        if (!bikeOwnerRepository.existsById(10)) {
            BikeOwner bo10 = new BikeOwner();
            bo10.setPhoneNumber("555-1234");
            bo10.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo10);
        }
        if (!bikeOwnerRepository.existsById(11)) {
            BikeOwner bo11 = new BikeOwner();
            bo11.setPhoneNumber("555-1234");
            bo11.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo11);
        }
        if (!bikeOwnerRepository.existsById(18)) {
            BikeOwner bo18 = new BikeOwner();
            bo18.setPhoneNumber("555-1234");
            bo18.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo18);
        }
        if (!bikeOwnerRepository.existsById(20)) {
            BikeOwner bo20 = new BikeOwner();
            bo20.setPhoneNumber("555-1234");
            bo20.setBirthDate(LocalDate.of(1990, 5, 20));
            bikeOwnerRepository.save(bo20);
        }

        if (!bikeRepository.findById("123e4567-e89b-12d3-a456-426614174001").isPresent()) {
            Bike bike1 = new Bike();
            bike1.setBikeQR("123e4567-e89b-12d3-a456-426614174001");
            bike1.setChassisNumber("123e4567-e89b-12d3-a456-426614174001"); // Match chassisNumber to bikeQR as per previous logic
            bike1.setBrand("Giant");
            bike1.setModel("Talon");
            bike1.setProductionYear(2022);
            bike1.setBikeSize(BikeSize.M);
            bike1.setMileage(1000);
            bike1.setGearType("Shimano");
            bike1.setEngineType("Electric");
            bike1.setPowerTrain("Chain Drive");
            bike1.setAccuCapacity(500.0);
            bike1.setMaxSupport(25.0);
            bike1.setMaxEnginePower(750.0);
            bike1.setNominalEnginePower(500.0);
            bike1.setEngineTorque(85.0);
            bike1.setLastTestDate(LocalDate.of(2024, 1, 1));
            bikeRepository.save(bike1);
        }
        if (!bikeRepository.findById("223e4567-e89b-12d3-a456-426614174002").isPresent()) {
            Bike bike2 = new Bike();
            bike2.setBikeQR("223e4567-e89b-12d3-a456-426614174002");
            bike2.setChassisNumber("223e4567-e89b-12d3-a456-426614174002"); // Match chassisNumber to bikeQR as per previous logic
            bike2.setBrand("Trek");
            bike2.setModel("Marlin");
            bike2.setProductionYear(2023);
            bike2.setBikeSize(BikeSize.L);
            bike2.setMileage(500);
            bike2.setGearType("SRAM");
            bike2.setEngineType("Electric");
            bike2.setPowerTrain("Belt Drive");
            bike2.setAccuCapacity(600.0);
            bike2.setMaxSupport(28.0);
            bike2.setMaxEnginePower(800.0);
            bike2.setNominalEnginePower(600.0);
            bike2.setEngineTorque(90.0);
            bike2.setLastTestDate(LocalDate.of(2024, 2, 1));
            bikeRepository.save(bike2);
        }

        if (!testBenchRepository.existsByTestBenchName("Test Bench 1")) {
            TestBench tb1 = new TestBench();
            tb1.setLastCalibrationDate(LocalDate.now());
            tb1.setLocation("Zone A");
            tb1.setStatus(Status.INACTIVE);
            tb1.setTestBenchName("Test Bench 1");
            tb1.setTechnician(null);
            testBenchRepository.save(tb1);
        }

        if (!testBenchRepository.existsByTestBenchName("Test Bench 2")) {
            TestBench tb2 = new TestBench();
            tb2.setLastCalibrationDate(LocalDate.now());
            tb2.setLocation("Zone A");
            tb2.setStatus(Status.INACTIVE);
            tb2.setTestBenchName("Test Bench 2");
            tb2.setTechnician(null);
            testBenchRepository.save(tb2);
        }

        if (!testBenchRepository.existsByTestBenchName("Test Bench 3")) {
            TestBench tb3 = new TestBench();
            tb3.setLastCalibrationDate(LocalDate.now());
            tb3.setLocation("Zone B");
            tb3.setStatus(Status.INACTIVE);
            tb3.setTestBenchName("Test Bench 3");
            tb3.setTechnician(null);
            testBenchRepository.save(tb3);
        }

        if (!testBenchRepository.existsByTestBenchName("Test Bench 4")) {
            TestBench tb4 = new TestBench();
            tb4.setLastCalibrationDate(LocalDate.now());
            tb4.setLocation("Zone B");
            tb4.setStatus(Status.INACTIVE);
            tb4.setTestBenchName("Test Bench 4");
            tb4.setTechnician(null);
            testBenchRepository.save(tb4);
        }

        if (!testBenchRepository.existsByTestBenchName("Test Bench 5")) {
            TestBench tb5 = new TestBench();
            tb5.setLastCalibrationDate(LocalDate.now());
            tb5.setLocation("Zone C");
            tb5.setStatus(Status.INACTIVE);
            tb5.setTestBenchName("Test Bench 5");
            tb5.setTechnician(null);
            testBenchRepository.save(tb5);
        }

        // Insert into technician
        if (!technicianRepository.existsById(2)) {
            Technician tech2 = new Technician();
            tech2.setId(2);
            technicianRepository.save(tech2);
        }
        if (!technicianRepository.existsById(7)) {
            Technician tech7 = new Technician();
            tech7.setId(7);
            technicianRepository.save(tech7);
        }
        if (!technicianRepository.existsById(12)) {
            Technician tech12 = new Technician();
            tech12.setId(12);
            technicianRepository.save(tech12);
        }
        if (!technicianRepository.existsById(13)) {
            Technician tech13 = new Technician();
            tech13.setId(13);
            technicianRepository.save(tech13);
        }
        if (!technicianRepository.existsById(14)) {
            Technician tech14 = new Technician();
            tech14.setId(14);
            technicianRepository.save(tech14);
        }

        // Insert into administrator
        if (!administratorRepository.existsById(3)) {
            Administrator admin3 = new Administrator();
            admin3.setId(3);
            admin3.setCompanyName("E-Bike Corp");
            administratorRepository.save(admin3);
        }
        if (!administratorRepository.existsById(16)) {
            Administrator admin16 = new Administrator();
            admin16.setId(16);
            admin16.setCompanyName("E-Nursing");
            administratorRepository.save(admin16);
        }

        // Insert into super_admin
        if (!superAdminRepository.existsById(4)) {
            SuperAdmin superAdmin4 = new SuperAdmin();
            superAdmin4.setId(4);
            superAdminRepository.save(superAdmin4);
        }

        // Insert into test_bench
        if (!testBenchRepository.existsByTestBenchName("Test Bench 1")) {
            TestBench tb1 = new TestBench();
            tb1.setLastCalibrationDate(LocalDate.now());
            tb1.setLocation("Zone A");
            tb1.setStatus(Status.valueOf("INACTIVE"));
            tb1.setTestBenchName("Test Bench 1");
            tb1.setTechnician(null);
            testBenchRepository.save(tb1);
        }
        if (!testBenchRepository.existsByTestBenchName("Test Bench 2")) {
            TestBench tb2 = new TestBench();
            tb2.setLastCalibrationDate(LocalDate.now());
            tb2.setLocation("Zone A");
            tb2.setStatus(Status.valueOf("INACTIVE"));
            tb2.setTestBenchName("Test Bench 2");
            tb2.setTechnician(null);
            testBenchRepository.save(tb2);
        }
        if (!testBenchRepository.existsByTestBenchName("Test Bench 3")) {
            TestBench tb3 = new TestBench();
            tb3.setLastCalibrationDate(LocalDate.now());
            tb3.setLocation("Zone B");
            tb3.setStatus(Status.valueOf("INACTIVE"));
            tb3.setTestBenchName("Test Bench 3");
            tb3.setTechnician(null);
            testBenchRepository.save(tb3);
        }
        if (!testBenchRepository.existsByTestBenchName("Test Bench 4")) {
            TestBench tb4 = new TestBench();
            tb4.setLastCalibrationDate(LocalDate.now());
            tb4.setLocation("Zone B");
            tb4.setStatus(Status.valueOf("INACTIVE"));
            tb4.setTestBenchName("Test Bench 4");
            tb4.setTechnician(null);
            testBenchRepository.save(tb4);
        }
        if (!testBenchRepository.existsByTestBenchName("Test Bench 5")) {
            TestBench tb5 = new TestBench();
            tb5.setLastCalibrationDate(LocalDate.now());
            tb5.setLocation("Zone C");
            tb5.setStatus(Status.valueOf("INACTIVE"));
            tb5.setTestBenchName("Test Bench 5");
            tb5.setTechnician(null);
            testBenchRepository.save(tb5);
        }

        // Insert into recent_activity
        if (!recentActivityRepository.existsByActivityAndDescriptionAndUserIdAndDate(Activity.CREATED_USER, "Created technician Mike", 4L, LocalDate.of(2025, 4, 26).atStartOfDay())) {
            RecentActivity ra1 = new RecentActivity();
            ra1.setActivity(Activity.CREATED_USER);
            ra1.setDescription("Created technician Mike");
            ra1.setDate(LocalDate.of(2025, 4, 26).atStartOfDay());
            ra1.setUserId(4);
            recentActivityRepository.save(ra1);
        }
        if (!recentActivityRepository.existsByActivityAndDescriptionAndUserIdAndDate(Activity.UPDATED_USER, "Updated information about Mike", 4L, LocalDate.of(2025, 4, 28).atStartOfDay())) {
            RecentActivity ra2 = new RecentActivity();
            ra2.setActivity(Activity.UPDATED_USER);
            ra2.setDescription("Updated information about Mike");
            ra2.setDate(LocalDate.of(2025, 4, 28).atStartOfDay());
            ra2.setUserId(4);
            recentActivityRepository.save(ra2);
        }
        if (!recentActivityRepository.existsByActivityAndDescriptionAndUserIdAndDate(Activity.INITIALIZED_TEST, "All tests started successfully", 4L, LocalDate.of(2025, 4, 28).atStartOfDay())) {
            RecentActivity ra3 = new RecentActivity();
            ra3.setActivity(Activity.INITIALIZED_TEST);
            ra3.setDescription("All tests started successfully");
            ra3.setDate(LocalDate.of(2025, 4, 28).atStartOfDay());
            ra3.setUserId(4);
            recentActivityRepository.save(ra3);
        }
        if (!recentActivityRepository.existsByActivityAndDescriptionAndUserIdAndDate(Activity.INITIALIZED_TEST, "All tests started successfully", 4L, LocalDate.of(2025, 4, 23).atStartOfDay())) {
            RecentActivity ra4 = new RecentActivity();
            ra4.setActivity(Activity.INITIALIZED_TEST);
            ra4.setDescription("All tests started successfully");
            ra4.setDate(LocalDate.of(2025, 4, 23).atStartOfDay());
            ra4.setUserId(4);
            recentActivityRepository.save(ra4);
        }
    }

}
