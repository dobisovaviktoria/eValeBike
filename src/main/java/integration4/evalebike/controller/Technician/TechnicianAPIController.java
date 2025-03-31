package integration4.evalebike.controller.technician;

import integration4.evalebike.controller.technician.dto.*;
import integration4.evalebike.domain.Bike;
import integration4.evalebike.domain.BikeOwner;
import integration4.evalebike.service.BikeOwnerService;
import integration4.evalebike.service.BikeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/technician")
public class TechnicianAPIController {

    private final BikeService bikeService;
    private final BikeOwnerService bikeOwnerService;
    private final BikeMapper bikeMapper;
    private final BikeOwnerMapper bikeOwnerMapper;

    public TechnicianAPIController(BikeService bikeService, BikeOwnerService bikeOwnerService, BikeMapper bikeMapper, BikeOwnerMapper bikeOwnerMapper) {
        this.bikeService = bikeService;
        this.bikeOwnerService = bikeOwnerService;
        this.bikeMapper = bikeMapper;
        this.bikeOwnerMapper = bikeOwnerMapper;
    }

    @PostMapping("/bikes")
    public ResponseEntity<BikeDto> createBike(@RequestBody @Valid final AddBikeDto addBikeDto) throws Exception{
        final Bike bike = bikeService.addBike(addBikeDto.brand(), addBikeDto.model(), addBikeDto.chassisNumber(), addBikeDto.productionYear(),
                addBikeDto.bikeSize(), addBikeDto.mileage(), addBikeDto.gearType(), addBikeDto.engineType(), addBikeDto.powerTrain(),
                addBikeDto.accuCapacity(), addBikeDto.maxSupport(), addBikeDto.maxEnginePower(), addBikeDto.nominalEnginePower(),
                addBikeDto.engineTorque(), addBikeDto.lastTestDate());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bikeMapper.toBikeDto(bike));
    }

    @PostMapping("/bikeOwners")
    public ResponseEntity<BikeOwnerDto> createBikeOwner(@RequestBody @Valid final AddBikeOwnerDto addBikeOwnerDto) {
        final BikeOwner bikeOwner = bikeOwnerService.addBikeOwner(addBikeOwnerDto.name(), addBikeOwnerDto.email(), addBikeOwnerDto.phoneNumber(), addBikeOwnerDto.birthDate());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bikeOwnerMapper.toBikeOwnerDto(bikeOwner));
    }
}
//this is not working.. this method is supposed to hit the endpoint till we get the completed test
//    @PostMapping("/start/{bikeQR}")
//    public Mono<String> startTest(@PathVariable String bikeQR, @RequestParam("testType") String testType, Principal principal) {
//        String technicianUsername = principal != null ? principal.getName() : "anonymous";
//
//        return Mono.justOrEmpty(bikeService.findById(bikeQR))
//                .switchIfEmpty(Mono.error(new RuntimeException("Bike not found with QR: " + bikeQR)))
//                .map(bike -> new TestRequestDTO(
//                        testType,
//                        bike.getAccuCapacity(),
//                        bike.getMaxSupport(),
//                        bike.getMaxEnginePower(),
//                        bike.getNominalEnginePower(),
//                        bike.getEngineTorque()
//                ))
//                .flatMap(testRequestDTO -> testBenchService.startTest(testRequestDTO, technicianUsername))
//                .map(response -> "redirect:/technician/loading?testId=" + response.getId())
//                .onErrorResume(e -> {
//                    String encodedError = java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
//                    return Mono.just("redirect:/technician/bike-dashboard?error=" + encodedError);
//                });
//    }