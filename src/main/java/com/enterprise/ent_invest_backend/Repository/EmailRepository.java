package com.enterprise.ent_invest_backend.Repository;

import com.enterprise.ent_invest_backend.Model.EmailRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<EmailRecord, String> {
}