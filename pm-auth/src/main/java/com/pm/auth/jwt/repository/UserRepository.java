package com.pm.auth.jwt.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pm.auth.jwt.model.User;


public interface UserRepository extends MongoRepository<User, String> {
  public Optional<User> findByUsername(String username);

  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);
}