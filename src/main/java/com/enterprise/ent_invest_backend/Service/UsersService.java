package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(String id);
    public Optional<User> getUserByEmail(String email);
    public String addUser(User user);
    public User updateUser(User updatedUser, String id);
    public void deleteUser(String id);
    public void deleteAllUsers();
    public String logout(HttpSession session);
    public String sendRecoveryCode(String userEmail);
    public boolean verifyRecoveryCode(String userEmail, String recoveryCode);
    public User updatePassword(String userEmail, String newPassword);

}
