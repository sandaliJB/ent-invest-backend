package com.enterprise.ent_invest_backend.Repository;

import com.enterprise.ent_invest_backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserEmail(String email);
}
