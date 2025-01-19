package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.Enterprise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnterpriseRepository extends MongoRepository<Enterprise, String> {
    List<Enterprise> findByUserId(String userId);
    List<Enterprise> findByEnterpriseName(String enterpriseName);
    List<Enterprise> findByCity(String city);
}
