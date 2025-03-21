package com.enterprise.ent_invest_backend.Controller;

import com.enterprise.ent_invest_backend.Dto.AuthRequest;
import com.enterprise.ent_invest_backend.Dto.AuthenticationResponse;
import com.enterprise.ent_invest_backend.Jwt.JwtService;
import com.enterprise.ent_invest_backend.Jwt.UserService;
import com.enterprise.ent_invest_backend.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Public welcome endpoint. No authentication required.
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is open to all";
    }

    /**
     * Sign up new user. Registers a new user in the system.
     */
    @PostMapping("/signup")
    public String addNewUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Get all users. Restricted to users with "ROLE_ADMIN" authority.
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByEmail/{userEmail}")
    public Optional<User> getUserByUserEmail(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    /**
     * Get user details by ID. Restricted to users with "ROLE_USER" authority.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    /**
     * Sign in to authenticate the user and get the JWT token.
     */
    @PostMapping("/signing")
    public AuthenticationResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserEmail());
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    /**
     * Delete user by ID. Restricted to users with "ROLE_ADMIN" authority.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    /**
     * Delete all users. Restricted to users with "ROLE_ADMIN" authority.
     */
    @DeleteMapping("/deleteAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteAll() {
        userService.deleteAllUsers();
    }

    /**
     * Update user details by ID. Requires authentication and authorized role.
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User updateUser(@RequestBody User user, @PathVariable String id) {
        return userService.updateUser(user, id);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        return userService.logout(session);
    }

    @PostMapping("/sendVerificationCode")
    public String sendRecoveryCode(@RequestBody Map<String, String> payload) {
        String userEmail = payload.get("userEmail");
        if (userEmail == null || userEmail.isBlank()) {
            throw new RuntimeException("Email cannot be empty.");
        }
        return userService.sendRecoveryCode(userEmail);
    }

    @PostMapping("/verifyRecoveryCode")
    public boolean verifyRecoveryCode(@RequestParam String userEmail, @RequestParam String recoveryCode) {
        return userService.verifyRecoveryCode(userEmail, recoveryCode);
    }

    @PostMapping("/updatePassword")
    public User updatePassword(@RequestParam String userEmail, @RequestParam String newPassword) {
        return userService.updatePassword(userEmail, newPassword);
    }
}
