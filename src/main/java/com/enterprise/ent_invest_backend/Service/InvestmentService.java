package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.Enterprise;
import com.enterprise.ent_invest_backend.Model.Investment;

import java.util.List;
import java.util.Optional;

public interface InvestmentService {
    public Investment createInvestment(Investment investment);
    public Optional<Investment> getInvestmentByInvestmentId(String investmentId);
    public Optional<Investment> getInvestmentByUserId(String userId);
    public Optional<Investment> getInvestmentByInvestorName(String investorName);
    public List<Investment> getInvestmentByBudgetLimit(String budgetLimit);
    public List<Investment> getAllInvestments();
    public Investment updateInvestment(String investmentId, Investment investmentDetails);
    public void deleteInvestment(String investmentId);
}
