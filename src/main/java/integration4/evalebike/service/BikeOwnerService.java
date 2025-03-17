package integration4.evalebike.service;

import integration4.evalebike.domain.BikeOwner;
import integration4.evalebike.domain.User;
import integration4.evalebike.repository.BikeOwnerRepository;
import integration4.evalebike.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeOwnerService {
    private final BikeOwnerRepository bikeOwnerRepository;
    private final UserRepository userRepository;

    @Autowired
    public BikeOwnerService(BikeOwnerRepository bikeOwnerRepository, UserRepository userRepository) {
        this.bikeOwnerRepository = bikeOwnerRepository;
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Transactional
    public User updateUser(int id, User updateUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        if (updateUser.getName() != null && !updateUser.getName().isEmpty()) {
            user.setName(updateUser.getName());
        }
        if (updateUser.getEmail() != null && !updateUser.getEmail().isEmpty()) {
            user.setEmail(updateUser.getEmail());
        }
        if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
            user.setPassword(updateUser.getPassword());
        }

        return userRepository.save(user);
    }

}
