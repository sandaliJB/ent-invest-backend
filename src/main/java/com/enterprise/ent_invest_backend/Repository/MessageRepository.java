package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByUserId(String userId);
    List<Message> findByMessageStatus(String messageStatus);
}
