package com.enterprise.ent_invest_backend.Repository;

import com.enterprise.ent_invest_backend.Model.FundTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FundTransactionRepository  extends MongoRepository<FundTransaction, String> {
    List<FundTransaction> findByProjectId(String projectId);
}