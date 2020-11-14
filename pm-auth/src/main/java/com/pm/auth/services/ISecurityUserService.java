package com.pm.auth.services;

public interface ISecurityUserService {
    String validatePasswordResetToken(String token);
}
