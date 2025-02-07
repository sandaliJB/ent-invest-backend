package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.EmailRecord;

public interface EmailService {
    void sendEmail(String recipient, String name, double amount, String projectId);
    boolean sendEmail(EmailRecord emailRequest);
}