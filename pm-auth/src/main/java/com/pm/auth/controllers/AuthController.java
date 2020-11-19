package com.pm.auth.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.pm.auth.jwt.payload.request.PasswordResetRequest;
import com.pm.auth.jwt.payload.request.SignupRequest;
import com.pm.auth.jwt.payload.response.MessageResponse;
import com.pm.auth.services.ResetPasswordService;
import com.pm.auth.services.UserSignInService;
import com.pm.auth.services.UserSignUpService;
import com.pm.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pm.auth.jwt.payload.request.LoginRequest;
import com.pm.auth.jwt.payload.response.JwtResponse;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserSignUpService userSignUpService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private UserSignInService userSignInService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userSignInService.singIn(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest, final HttpServletRequest request) {
        return ResponseEntity.ok(userSignUpService.singUp(signupRequest));
    }

    //TODO FOR FUTURE WORKS-DO NOT REMOVE THIS
    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    // Generate a token and email with password reset link.
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(final HttpServletRequest httpRequest, @RequestBody PasswordResetRequest passwordResetRequest) {
        MessageResponse returnMessage = resetPasswordService.createPasswordResetTokenForUser(httpRequest, passwordResetRequest.getEmail());
        return new ResponseEntity(returnMessage, HttpStatus.OK);
    }


    //Validate password reset token and redirect to password reset page.
    @GetMapping("/changePassword")
    public RedirectView changePassword(@RequestParam("token") final String token) {

        //check token is valid

        //check token is expired

        // String returnMessage = resetPasswordService.createPasswordResetTokenForUser( email);
        return new RedirectView("http://localhost:8081/resetPassword");
        //return new ResponseEntity("Redirect to password change page", HttpStatus.OK);
    }

    // Save password
    @PostMapping("/savePassword")
    public ResponseEntity<?> savePassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        resetPasswordService.savePassword(passwordResetRequest.getToken(), passwordResetRequest.getPassword());
        return new ResponseEntity("New password saved successfully", HttpStatus.OK);

    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> changeUserPassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String email) {
        resetPasswordService.updatePassword(oldPassword, newPassword, email);
        return new ResponseEntity("Password changed successfully", HttpStatus.OK);
    }


    @GetMapping("/testGetMapping")
    public void TestGetMapping() {
        System.out.println("In get mapping");
    }

}