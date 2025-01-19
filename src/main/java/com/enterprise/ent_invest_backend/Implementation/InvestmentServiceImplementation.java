package com.enterprise.ent_invest_backend.Implementation;
import com.enterprise.ent_invest_backend.Model.Investment;
import com.enterprise.ent_invest_backend.Repository.InvestmentRepository;
import com.enterprise.ent_invest_backend.Service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentServiceImplementation implements InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public Investment createInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public Optional<Investment> getInvestmentByInvestmentId(String investmentId) {
        return investmentRepository.findByInvestmentId(investmentId);
    }

    @Override
    public Optional<Investment> getInvestmentByUserId(String userId) {
        return investmentRepository.findInvestmentByUserId(userId);
    }

    @Override
    public Optional<Investment> getInvestmentByInvestorName(String investorName) {
        return investmentRepository.findByInvestorName(investorName);
    }

    @Override
    public List<Investment> getInvestmentByBudgetLimit(String budgetLimit) {
        return investmentRepository.findByBudgetLimit(budgetLimit);
    }

    @Override
    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    @Override
    public Investment updateInvestment(String investmentId, Investment investmentDetails) {
        return investmentRepository.findById(investmentId).map(existingInvestment -> {
            // Update modifiable fields
            if (investmentDetails.getUserId() != null && !investmentDetails.getUserId().isBlank()) {
                existingInvestment.setUserId(investmentDetails.getUserId());
            }
            if (investmentDetails.getInvestorName() != null && !investmentDetails.getInvestorName().isBlank()) {
                existingInvestment.setInvestorName(investmentDetails.getInvestorName());
            }
            if (investmentDetails.getInvestorJob() != null && !investmentDetails.getInvestorJob().isBlank()) {
                existingInvestment.setInvestorJob(investmentDetails.getInvestorJob());
            }
            if (investmentDetails.getInvestorInterest() != null && !investmentDetails.getInvestorInterest().isBlank()) {
                existingInvestment.setInvestorInterest(investmentDetails.getInvestorInterest());
            }
            if (investmentDetails.getOtherDetails() != null && !investmentDetails.getOtherDetails().isBlank()) {
                existingInvestment.setOtherDetails(investmentDetails.getOtherDetails());
            }
            if (investmentDetails.getBudgetLimit() != null && !investmentDetails.getBudgetLimit().isBlank()) {
                existingInvestment.setBudgetLimit(investmentDetails.getBudgetLimit());
            }
            if (investmentDetails.getAddress() != null && !investmentDetails.getAddress().isBlank()) {
                existingInvestment.setAddress(investmentDetails.getAddress());
            }
            if (investmentDetails.getTelNumber() != null && !investmentDetails.getTelNumber().isBlank()) {
                existingInvestment.setTelNumber(investmentDetails.getTelNumber());
            }
            if (investmentDetails.getImageName() != null && !investmentDetails.getImageName().isBlank()) {
                existingInvestment.setImageName(investmentDetails.getImageName());
            }
            if (investmentDetails.getImageData() != null && investmentDetails.getImageData().length > 0) {
                existingInvestment.setImageData(investmentDetails.getImageData());
            }
            if (investmentDetails.getContentType() != null && !investmentDetails.getContentType().isBlank()) {
                existingInvestment.setContentType(investmentDetails.getContentType());
            }

            // Save and return the updated investment
            return investmentRepository.save(existingInvestment);
        }).orElseThrow(() -> new RuntimeException("Investment not found with ID: " + investmentId));
    }

    @Override
    public void deleteInvestment(String investmentId) {
        investmentRepository.deleteById(investmentId);
    }
}
