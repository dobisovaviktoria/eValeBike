package integration4.evalebike.controller.bikeOwner;

import integration4.evalebike.controller.bikeOwner.dto.BikeOwnerProfileDto;
import integration4.evalebike.security.CustomUserDetails;
import integration4.evalebike.service.BikeOwnerService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bikeOwner")
public class BikeOwnerApiController {
    private final BikeOwnerService bikeOwnerService;

    public BikeOwnerApiController(BikeOwnerService bikeOwnerService) {
        this.bikeOwnerService = bikeOwnerService;
    }

//    @GetMapping("/profile")
//    public BikeOwnerProfileDto getProfile(Authentication auth) {
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//        return bikeOwnerService.getProfile(userDetails.getUserId());
//    }
//
//    @PostMapping("/profile")
//    public void updateProfile(@RequestBody BikeOwnerProfileDto dto, Authentication auth) {
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//        bikeOwnerService.updateProfile(userDetails.getUserId(), dto);
//    }
}

