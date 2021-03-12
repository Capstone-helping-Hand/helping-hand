package com.codeup.helpinghand.services;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Value("helping-hand-admin@sahh.org")
    private String from;

    public void prepareDonationEmail(Donation donation, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(donation.getDonator().getEmail());
        message.setSubject(subject);
        message.setText(body);

        try {
            this.emailSender.send(message);
        } catch (MailException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void prepareRequestEmail(Request request, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(request.getUser().getEmail());
        message.setSubject(subject);
        message.setText(body);

        try {
            this.emailSender.send(message);
        } catch (MailException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
