package com.pm.auth.jwt.services;

import com.pm.common.persistence.model.User;
import com.pm.auth.persistent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResetPasswordServiceImpl implements  ResetPasswordService{
    @Autowired
    UserRepository userRepository;

    //@Autowired
    //private JavaMailSender mailSender;

    @Override
    public String createPasswordResetTokenForUser(String email) throws Exception
    {
        User user = userRepository.findByEmail(email);

        if (user == null ){
            throw new Exception("No user is registered with the given email. Please try with the correct email again.");
            //return "No user is registered with the given email. Please try with the correct email again.";
        }

        final String token = UUID.randomUUID().toString();



        return "Password reset link is sent to your email address. Please check your email.";
    }
}
