package com.enterprise.ent_invest_backend.Controller;

import com.enterprise.ent_invest_backend.Model.Investment;
import com.enterprise.ent_invest_backend.Service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;


    // Create a new Investment
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Investment> createInvestment(@RequestBody Investment investment) {
        Investment createdInvestment = investmentService.createInvestment(investment);
        return ResponseEntity.ok(createdInvestment);
    }

    // Get Investment by InvestmentID
    @GetMapping("/getInvestmentByInvestmentId/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Optional<Investment>> getInvestmentByInvestmentId(@PathVariable("id") String investmentId) {
        Optional<Investment> investment = investmentService.getInvestmentByInvestmentId(investmentId);
        return ResponseEntity.ok(investment);
    }


    // Get Investment by UserID
    @GetMapping("/getInvestmentByUserId/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Optional<Investment>> getInvestmentByUserId(@PathVariable("id") String userId) {
        Optional<Investment> investment = investmentService.getInvestmentByUserId(userId);
        return ResponseEntity.ok(investment);
    }


    // Get Investment by BudgetLimit
    @GetMapping("/getInvestmentByBudgetLimit/{budgetLimit}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Investment>> getInvestmentByBudgetLimit(@PathVariable("budgetLimit") String budgetLimit) {
        List<Investment> investment = investmentService.getInvestmentByBudgetLimit(budgetLimit);
        return ResponseEntity.ok(investment);
    }

    // Get All Investors
    @GetMapping("/getAllInvestors")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Investment>> getAllInvestors() {
        List<Investment> investment = investmentService.getAllInvestments();
        return ResponseEntity.ok(investment);
    }

    // Update Investment
    @PutMapping("/updateInvestment/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Investment> updateInvestment(
            @PathVariable("id") String investmentId,
            @RequestBody Investment investmentDetails) {
        Investment updatedInvestment = investmentService.updateInvestment(investmentId, investmentDetails);
        return ResponseEntity.ok(updatedInvestment);
    }

    // Delete Investor
    @DeleteMapping("/deleteInvestment/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> deleteInvestment(@PathVariable("id") String investmentId) {
        investmentService.deleteInvestment(investmentId);
        return ResponseEntity.ok("Investor with ID " + investmentId + " has been deleted successfully.");
    }
}