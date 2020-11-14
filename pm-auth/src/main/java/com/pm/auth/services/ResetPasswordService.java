package com.pm.auth.services;

import javax.servlet.http.HttpServletRequest;

public interface ResetPasswordService {
    public String createPasswordResetTokenForUser(HttpServletRequest httpRequest , String email);

    void updatePassword(String oldPassword, String newPassword, String email);

    void savePassword(String token, String newPassword);
}
