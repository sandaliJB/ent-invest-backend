package com.enterprise.ent_invest_backend.Jwt;

import com.enterprise.ent_invest_backend.Model.User;
import com.enterprise.ent_invest_backend.Repository.UserRepository;
import com.enterprise.ent_invest_backend.Service.UsersService;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, String> recoveryCodes = new HashMap<>();

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
        return user.getUserId();
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

    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }

    public String sendRecoveryCode(String userEmail) {
        // Find user by email
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        if (optionalUser.isEmpty()) {
            return "User Not Registered";
        }
        // Generate a random 6-digit code
        String recoveryCode = String.format("%06d", new Random().nextInt(999999));
        // Save the code in a temporary storage (e.g., in-memory map, Redis, or database)
        recoveryCodes.put(userEmail, recoveryCode);
        // Create HTML-styled email
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            // Email body with styling
            String emailBody = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                    ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                    ".header { background-color: #135bf2; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                    ".content { padding: 20px; font-size: 16px; color: #333; }" +
                    ".content p { line-height: 1.6; }" +
                    ".recovery-code { font-weight: bold; color: #135bf2; font-size: 18px; }" +
                    ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                    ".footer p { margin: 5px 0; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div class='header'><h2>Account Recovery</h2></div>" +
                    "<div class='content'>" +
                    "<p>Dear " + optionalUser.get().getFirstName() + " " + optionalUser.get().getLastName() + ",</p>" +
                    "<p>We received a request to reset the password for your account. Use the code below to proceed with password recovery:</p>" +
                    "<p class='recovery-code'>" + recoveryCode + "</p>" +
                    "<p>If you did not request this change, please contact our support team immediately.</p>" +
                    "<p>Warm regards,</p>" +
                    "<p><strong>BkTicketing Support Team</strong></p>" +
                    "</div>" +
                    "<div class='footer'>" +
                    "<p>&copy; 2024 BkTicketing LK. All rights reserved.</p>" +
                    "<p>If you have any questions, please contact us at support@bkticketing.lk</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            // Configure and send email
            helper.setTo(userEmail);
            helper.setSubject("Password Recovery Code");
            helper.setText(emailBody, true); // Set true to enable HTML content
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send recovery email: " + e.getMessage());
        }
        return recoveryCode; // Optionally return it to the frontend for testing purposes
    }

    public boolean verifyRecoveryCode(String userEmail, String recoveryCode) {
        String storedCode = recoveryCodes.get(userEmail);
        if (storedCode != null && storedCode.equals(recoveryCode)) {
            recoveryCodes.remove(userEmail);
            return true;
        }
        return false;
    }

    public User updatePassword(String userEmail, String newPassword) {
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No user found with email: " + userEmail);
        }
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }
}
