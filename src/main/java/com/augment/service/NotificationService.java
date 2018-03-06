package com.augment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    private final String mailUsername;
    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(@Value("${mail.username}") final String mailUsername, final JavaMailSender javaMailSender) {
        this.mailUsername = mailUsername;
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(final String username) throws MailException {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(username);
        mailMessage.setFrom(mailUsername);
        mailMessage.setSubject("Temporary subject");
        mailMessage.setText("Temporary body");

        javaMailSender.send(mailMessage);
    }
}
