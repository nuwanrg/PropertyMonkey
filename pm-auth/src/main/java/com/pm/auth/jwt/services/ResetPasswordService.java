package com.pm.auth.jwt.services;

public interface ResetPasswordService {
    public String createPasswordResetTokenForUser(String email) throws Exception;
}
