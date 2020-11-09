package com.pm.auth.services;

public interface ResetPasswordService {
    public String createPasswordResetTokenForUser(String email) throws Exception;
}
