package com.enterprise.ent_invest_backend.Controller;

import com.enterprise.ent_invest_backend.Model.EmailRecord;
import com.enterprise.ent_invest_backend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend requests
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody Map<String, Object> payload) {
        String recipient = (String) payload.get("recipient");
        String name = (String) payload.get("name");
        double amount = Double.parseDouble(payload.get("amount").toString());
        String projectId = (String) payload.get("projectId");

        emailService.sendEmail(recipient, name, amount, projectId);
        return "Email sent successfully!";
    }



    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRecord emailRequest) {
        boolean isSent = emailService.sendEmail(emailRequest);
        if (isSent) {
            return ResponseEntity.ok("Email sent successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to send email.");
        }
    }
}