package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserEmail(String email);
}
