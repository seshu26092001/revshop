package com.Revshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Method to send a registration confirmation email
    public void sendRegistrationEmail(String email, String name, String role) {
        String subject = "Registration Successful";
        String body = String.format(
            "Dear %s,\n\n" +
            "Thank you for registering as a %s with us!\n" +
            "You can now log in using your credentials.\n\n" +
            "Best Regards,\nRevshop Team", name, role
        );

        sendEmail(email, subject, body);
    }

    // Method to send a welcome-back email
    public void sendWelcomeBackEmail(String email, String name) {
        String subject = "Welcome Back!";
        String body = String.format(
            "Dear %s,\n\n" +
            "Welcome back to Revshop! We're glad to see you again.\n" +
            "Feel free to explore our latest offerings.\n\n" +
            "Best Regards,\nRevshop Team", name
        );

        sendEmail(email, subject, body);
    }

    // Helper method to send an email
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
