package com.pm.auth.jwt.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PasswordResetRequest {
    @NotNull
    private String email;

    @NotNull
    private String token;

    @NotNull
    private String password;

}
