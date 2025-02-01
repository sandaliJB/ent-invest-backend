package com.enterprise.ent_invest_backend.Implementation;

import com.enterprise.ent_invest_backend.Model.FundTransaction;
import com.enterprise.ent_invest_backend.Model.FundingProject;
import com.enterprise.ent_invest_backend.Repository.FundTransactionRepository;
import com.enterprise.ent_invest_backend.Repository.FundingProjectRepository;
import com.enterprise.ent_invest_backend.Service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundingServiceImplementation implements FundingService {
    @Autowired
    private FundingProjectRepository fundingProjectRepository;

    @Autowired
    private FundTransactionRepository fundTransactionRepository;

    @Override
    public FundingProject createProject(FundingProject fundingProject) {
        return fundingProjectRepository.save(fundingProject);
    }

    @Override
    public List<FundingProject> getAllProjects() {
        return fundingProjectRepository.findAll();
    }

    @Override
    public FundTransaction fundProject(String projectId, String investorId, double amount) {
        FundingProject fundingProject = fundingProjectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Funding project not found"));

        // Update the current funding amount
        fundingProject.setCurrentAmount(fundingProject.getCurrentAmount() + amount);
        fundingProjectRepository.save(fundingProject);

        // Create a new fund transaction
        FundTransaction transaction = new FundTransaction();
        transaction.setProjectId(projectId);
        transaction.setInvestorId(investorId);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        return fundTransactionRepository.save(transaction);
    }
}
