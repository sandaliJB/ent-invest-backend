package com.enterprise.ent_invest_backend.Service;


import com.enterprise.ent_invest_backend.Model.FundTransaction;
import com.enterprise.ent_invest_backend.Model.FundingProject;

import java.util.List;

public interface FundingService {
    FundingProject createProject(FundingProject fundingProject);
    List<FundingProject> getAllProjects();
    FundTransaction fundProject(String projectId, String investorId, double amount);
}