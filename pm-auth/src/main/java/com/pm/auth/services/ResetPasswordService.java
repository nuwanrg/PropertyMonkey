package com.pm.auth.services;

import javax.servlet.http.HttpServletRequest;

public interface ResetPasswordService {
    public String createPasswordResetTokenForUser(HttpServletRequest httpRequest , String email);
}
