package com.enterprise.ent_invest_backend.Controller;

import com.enterprise.ent_invest_backend.Model.Enterprise;
import com.enterprise.ent_invest_backend.Service.EnterpriseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    // Create a new Enterprise
    @PostMapping("/add")
    public ResponseEntity<?> addEnterprise(
            @RequestPart("enterprise") String enterpriseJson,
            @RequestPart("imageFile") MultipartFile imageFile) throws IOException {

        try {
            // Convert JSON string to Enterprise object
            ObjectMapper objectMapper = new ObjectMapper();
            Enterprise enterprise = objectMapper.readValue(enterpriseJson, Enterprise.class);

            // Call the service layer to save the enterprise and handle the image file
            Enterprise createdEnterprise = enterpriseService.createEnterprise(enterprise, imageFile);

            // Return the created enterprise object with status 201
            return new ResponseEntity<>(createdEnterprise, HttpStatus.CREATED);

        } catch (Exception e) {
            // If there is any error, return the error message with status 500
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Enterprise by ID
    @GetMapping("/getEnterpriseById/{id}")
    public ResponseEntity<Optional<Enterprise>> getEnterpriseById(@PathVariable("id") String enterpriseId) {
        Optional<Enterprise> enterprise = enterpriseService.getEnterpriseByEnterpriseId(enterpriseId);
        return ResponseEntity.ok(enterprise);
    }

    // Get Enterprise by UserID
    @GetMapping("/getEnterpriseByUserId/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Enterprise>> getEnterpriseByUserId(@PathVariable("id") String userId) {
        List<Enterprise> enterprise = enterpriseService.getEnterpriseByUserId(userId);
        return ResponseEntity.ok(enterprise);
    }

    // Get Enterprise by City
    @GetMapping("/getEnterpriseByCity/{city}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Enterprise>> getEnterpriseByCity(@PathVariable("city") String city) {
        List<Enterprise> enterprise = enterpriseService.getEnterpriseByCity(city);
        return ResponseEntity.ok(enterprise);
    }

    // Get Enterprise by Name
    @GetMapping("/getEnterpriseByName/{name}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Enterprise>> getEnterpriseByName(@PathVariable("name") String enterpriseName) {
        List<Enterprise> enterprise = enterpriseService.getEnterpriseByName(enterpriseName);
        return ResponseEntity.ok(enterprise);
    }

    // Get All Enterprises
    @GetMapping("/getAll")
    public ResponseEntity<List<Enterprise>> getAllEnterprises() {
        List<Enterprise> enterprise = enterpriseService.getAllEnterprises();
        return ResponseEntity.ok(enterprise);
    }

    // Update Enterprise
    @PutMapping("/updateEnterprise/{id}")
    public ResponseEntity<Enterprise> updateEnterprise(
            @PathVariable("id") String enterpriseId,
            @RequestBody Enterprise enterpriseDetails) {
        Enterprise updatedEnterprise = enterpriseService.updateEnterprise(enterpriseId, enterpriseDetails);
        return ResponseEntity.ok(updatedEnterprise);
    }

    // Delete Enterprise
    @DeleteMapping("/deleteEnterprise/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> deleteEnterprise(@PathVariable("id") String enterpriseId) {
        enterpriseService.deleteEnterprise(enterpriseId);
        return ResponseEntity.ok("Enterprise with ID " + enterpriseId + " has been deleted successfully.");
    }
}
