package com.pm.auth.persistent.repository;

import com.pm.auth.model.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordTokenRepository extends MongoRepository<PasswordResetToken , String> {
}
