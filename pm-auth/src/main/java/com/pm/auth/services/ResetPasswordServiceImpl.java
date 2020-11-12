package com.pm.auth.services;

import com.pm.auth.exception.PasswordResetException;
import com.pm.auth.model.PasswordResetToken;
import com.pm.auth.persistent.repository.PasswordTokenRepository;
import com.pm.common.persistence.model.User;
import com.pm.auth.persistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@Service
public class ResetPasswordServiceImpl implements  ResetPasswordService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;


    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    @Autowired
    private Environment env;

    @Override
    public String createPasswordResetTokenForUser(HttpServletRequest request, String email)
    {
        User user = userRepository.findByEmail(email);

        if (user == null ){
            throw new PasswordResetException("No user is registered with the given email. Please try with the correct email again.");
            //return "No user is registered with the given email. Please try with the correct email again.";
        }

        final String token = UUID.randomUUID().toString();
        createToken(token , user);

        mailService.sendPasswordResetMail(getAppUrl(request), request.getLocale(), token, user);
        return "Password reset link is sent to your email address. Please check your email.";
    }

    public void createToken(String token , User user){
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
