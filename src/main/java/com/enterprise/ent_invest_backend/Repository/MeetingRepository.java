package com.enterprise.ent_invest_backend.Repository;

import com.enterprise.ent_invest_backend.Model.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeetingRepository extends MongoRepository<Meeting, String> {
}