package com.pm.auth.services;

import com.pm.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MailService {
    @Autowired(required = true)
    private JavaMailSender mailSender;

    public void sendPasswordResetMail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = "message.resetPassword";
        mailSender.send( constructEmail("Reset Password", message + " \r\n" + url, user));
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo("nuwan_rajitha@outlook.com"); //user.getEmail()
        //email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
