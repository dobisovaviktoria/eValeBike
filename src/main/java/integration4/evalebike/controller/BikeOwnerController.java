package integration4.evalebike.controller;

import integration4.evalebike.domain.User;
import integration4.evalebike.service.BikeOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class BikeOwnerController {
    private final BikeOwnerService bikeOwnerService;

    @Autowired
    public BikeOwnerController(BikeOwnerService bikeOwnerService) {
        this.bikeOwnerService = bikeOwnerService;
    }

    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable Integer id, Model model) {
        try {
            User user = bikeOwnerService.getUserById(id);
            model.addAttribute("user", user);
            return "templates/user/userProfile";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "User not found");
            return "templates/user/userProfile";
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUserProfile(@PathVariable Integer id, @RequestBody User updatedUser) {
        try {
            User user = bikeOwnerService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}