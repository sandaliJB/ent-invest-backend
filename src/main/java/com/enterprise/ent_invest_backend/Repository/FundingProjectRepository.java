package com.enterprise.ent_invest_backend.Repository;
import com.enterprise.ent_invest_backend.Model.FundingProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundingProjectRepository extends MongoRepository<FundingProject, String> {
}

