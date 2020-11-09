package com.pm.auth.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.pm.auth.services.UserSignInService;
import com.pm.auth.services.UserSignUpService;
import com.pm.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pm.auth.jwt.payload.request.LoginRequest;
import com.pm.auth.jwt.payload.response.JwtResponse;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserSignUpService userSignUpService;

    @Autowired
    UserSignInService userSignInService;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userSignInService.singIn(loginRequest.getUsername(), loginRequest.getPassword()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, final HttpServletRequest request) {
        return ResponseEntity.ok(userSignUpService.singUp(user));
    }


    //TODO FOR FUTURE WORKS-DO NOT REMOVE THIS
    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @GetMapping("/testGetMapping")
    public void TestGetMapping(){
        System.out.println("In get mapping");
    }

}