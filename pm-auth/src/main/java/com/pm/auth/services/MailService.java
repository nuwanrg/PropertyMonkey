package com.pm.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired(required = true)
    private JavaMailSender mailSender;

    public void sendPasswordResetMail(final String contextPath, final String token, final String email) {
        final String url = contextPath + "/auth/changePassword?token=" + token;
        final String message = "message.resetPassword";
        mailSender.send(constructEmail("Reset Password", message + " \r\n" + url, email));
    }

    private SimpleMailMessage constructEmail(String subject, String body, String email) {
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject(subject);
        mail.setText(body);
        mail.setTo(email);
        //mail.setFrom(env.getProperty("support.email"));
        return mail;
    }

    public void sendSignUpMail(final String email) {
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("Welcome to PropertyMonkey");
        mail.setText("message.Signup");
        mail.setTo(email);
        mailSender.send(mail);
    }
}
