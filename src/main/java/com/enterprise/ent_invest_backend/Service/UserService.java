package com.enterprise.ent_invest_backend.Service;
import com.enterprise.ent_invest_backend.Model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public Optional<User> getUserById(String userId);
    public Optional<User> getUserByUserEmail(String userEmail);
    public List<User> getAllUsers();
    public User updateUser(String userId, User userDetails);
    public void deleteUser(String userId);
}
