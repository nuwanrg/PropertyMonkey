package com.pm.auth.jwt.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PasswordResetRequest {

    private String email;

    private String token;

    private String password;

}
