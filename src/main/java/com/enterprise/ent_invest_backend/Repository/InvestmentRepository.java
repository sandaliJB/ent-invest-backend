package com.enterprise.ent_invest_backend.Repository;

import com.enterprise.ent_invest_backend.Model.Investment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface InvestmentRepository extends MongoRepository<Investment, String> {
    Optional<Investment> findByInvestmentId(String investmentId);
    Optional<Investment> findInvestmentByUserId(String userId);
    Optional<Investment> findByInvestorName(String investorName);
    List<Investment> findByBudgetLimit(String budgetLimit);
}
