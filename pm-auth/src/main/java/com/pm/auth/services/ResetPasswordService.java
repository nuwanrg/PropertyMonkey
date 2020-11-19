package com.pm.auth.services;

import com.pm.auth.jwt.payload.response.MessageResponse;

import javax.servlet.http.HttpServletRequest;

public interface ResetPasswordService {
    public MessageResponse createPasswordResetTokenForUser(HttpServletRequest httpRequest , String email);

    void updatePassword(String oldPassword, String newPassword, String email);

    void savePassword(String token, String newPassword);
}
