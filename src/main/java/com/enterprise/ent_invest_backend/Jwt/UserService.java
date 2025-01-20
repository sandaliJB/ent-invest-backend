package com.enterprise.ent_invest_backend.Jwt;

import com.enterprise.ent_invest_backend.Model.User;
import com.enterprise.ent_invest_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retrieve all users from the database.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieve a specific user by ID.
     */
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieve a specific user by email.
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    /**
     * Add a new user to the system. Password is encoded before saving.
     */
    public String addUser(User user) {
        if (userRepository.findByUserEmail(user.getUserEmail()).isPresent()) {
            return "User already registered as a user";
        }
        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    /**
     * Update an existing user by ID. Fields are updated if they are provided.
     */
    public User updateUser(User updatedUser, String id) {
        return userRepository.findById(id).map(existingUser -> {
            // Update fields if provided
            if (updatedUser.getFirstName() != null) {
                existingUser.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                existingUser.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getUserEmail() != null) {
                existingUser.setUserEmail(updatedUser.getUserEmail());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            if (updatedUser.getUserRole() != null) {
                existingUser.setUserRole(updatedUser.getUserRole());
            }

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    /**
     * Delete a user by ID.
     */
    public void deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    /**
     * Delete all users.
     */
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
