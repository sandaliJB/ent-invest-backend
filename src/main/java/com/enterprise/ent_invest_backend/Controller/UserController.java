package com.enterprise.ent_invest_backend.Controller;
import com.enterprise.ent_invest_backend.Model.User;
import com.enterprise.ent_invest_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    // Create a new user
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get user by ID
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String userId) {
        Optional<User> user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserByEmail/{id}")
    public Optional<User> getUserByEmail(@PathVariable("id") String userEmail) {
        return userService.getUserByUserEmail(userEmail);
    }

    // Get all users
    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Update user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
    }
}
