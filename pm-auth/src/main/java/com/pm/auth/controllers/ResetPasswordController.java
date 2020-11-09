package com.pm.auth.controllers;

import com.pm.auth.services.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping("/auth/resetPassword")
    public ResponseEntity<String> resetPassword(final HttpServletRequest httpRequest , @RequestParam("email") final String email ) {
        try {
            String returnMessage = resetPasswordService.createPasswordResetTokenForUser(email);
            return new ResponseEntity(returnMessage , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}