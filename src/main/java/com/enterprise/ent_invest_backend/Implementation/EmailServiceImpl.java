package com.enterprise.ent_invest_backend.Implementation;
import com.enterprise.ent_invest_backend.Model.EmailRecord;
import com.enterprise.ent_invest_backend.Repository.EmailRepository;
import com.enterprise.ent_invest_backend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public void sendEmail(String recipient, String name, double amount, String projectId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipient);
            helper.setSubject("Payment Confirmation - Fund Transfer ID: " + projectId);
            helper.setFrom("seedlink594@gmail.com");

            String emailContent = """
                <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #007bff;">Payment Confirmation</h2>
                    <p>Dear <strong>%s</strong>,</p>
                    <p>Your payment of <strong>$%.2f</strong> has been successfully processed.</p>
                    <p><strong>Fund Transfer ID:</strong> %s</p>
                    <hr>
                    <p style="font-size: 14px; color: gray;">If you have any issues, please contact support.</p>
                    <p>Best regards,<br><strong>SeedLink Team</strong></p>
                </body>
                </html>
            """.formatted(name, amount, projectId);

            helper.setText(emailContent, true);
            mailSender.send(message);

            // Store the email in MongoDB
            EmailRecord emailRecord = new EmailRecord(recipient, "seedlink594@gmail.com", "Payment Confirmation", emailContent);
            emailRepository.save(emailRecord);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
    }

    /*@Override
    public void sendEmailEnt(String recipient, String name, String message, String projectId) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setTo(recipient);
            helper.setSubject("Payment Confirmation - Fund Transfer ID: " + projectId);
            helper.setFrom("seedlink594@gmail.com");

            String emailContent = """
                <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #007bff;">Payment Confirmation</h2>
                    <p>Dear <strong>%s</strong>,</p>
                    <p>Your payment of <strong>$%.2f</strong> has been successfully processed.</p>
                    <p><strong>Fund Transfer ID:</strong> %s</p>
                    <hr>
                    <p style="font-size: 14px; color: gray;">If you have any issues, please contact support.</p>
                    <p>Best regards,<br><strong>SeedLink Team</strong></p>
                </body>
                </html>
            """.formatted(name, message, projectId);

            helper.setText(emailContent, true);
            mailSender.send(msg);

            // Store the email in MongoDB
            EmailRecord emailRecord = new EmailRecord(recipient, "seedlink594@gmail.com", "Payment Confirmation", emailContent);
            emailRepository.save(emailRecord);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");*/
    @Override
    public boolean sendEmail(EmailRecord emailRequest) {
        boolean status;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("jayasinghesb2000@gmail.com");
            helper.setSubject("Payment Confirmation - Fund Transfer ID: ");
            helper.setFrom("seedlink594@gmail.com");

            String emailContent = """
                <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #007bff;">Payment Confirmation</h2>
                    <p>Dear <strong>%s</strong>,</p>
                    <p>Your payment of <strong>$%.2f</strong> has been successfully processed.</p>
                    <p><strong>Fund Transfer ID:</strong> %s</p>
                    <hr>
                    <p style="font-size: 14px; color: gray;">If you have any issues, please contact support.</p>
                    <p>Best regards,<br><strong>SeedLink Team</strong></p>
                </body>
                </html>
            """;

            helper.setText(emailContent, true);
            mailSender.send(message);
            status =  true;

            // Store the email in MongoDB
            EmailRecord emailRecord = new EmailRecord(emailRequest.getSender(), "seedlink594@gmail.com", "Payment Confirmation", emailContent);
            emailRepository.save(emailRecord);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
        return status;
    }
}
