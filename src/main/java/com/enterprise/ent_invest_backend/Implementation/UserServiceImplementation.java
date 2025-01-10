package com.enterprise.ent_invest_backend.Implementation;

import com.enterprise.ent_invest_backend.Model.User;
import com.enterprise.ent_invest_backend.Repository.UserRepository;
import com.enterprise.ent_invest_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Optional<User> getUserByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String userId, User userDetails) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        if (userDetails.getUserEmail() != null && !userDetails.getUserEmail().isBlank()) {
            existingUser.setUserEmail(userDetails.getUserEmail());
        }
        if (userDetails.getFirstName() != null && !userDetails.getFirstName().isBlank()) {
            existingUser.setFirstName(userDetails.getFirstName());
        }
        if (userDetails.getLastName() != null && !userDetails.getLastName().isBlank()) {
            existingUser.setLastName(userDetails.getLastName());
        }
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            existingUser.setPassword(userDetails.getPassword());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
