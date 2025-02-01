package com.enterprise.ent_invest_backend.Controller;

import com.enterprise.ent_invest_backend.Model.FundTransaction;
import com.enterprise.ent_invest_backend.Model.FundingProject;
import com.enterprise.ent_invest_backend.Service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funding")
public class FundingController {
    @Autowired
    private FundingService fundingService;

    @PostMapping("/add")
    public FundingProject createProject(@RequestBody FundingProject fundingProject) {
        return fundingService.createProject(fundingProject);
    }
    @GetMapping("/getAll")
    public List<FundingProject> getAllProjects() {
        return fundingService.getAllProjects();
    }

    @PostMapping("/projects/{projectId}")
    public FundTransaction fundProject(@PathVariable String projectId, @RequestParam String investorId, @RequestParam double amount) {
        return fundingService.fundProject(projectId, investorId, amount);
    }

}
