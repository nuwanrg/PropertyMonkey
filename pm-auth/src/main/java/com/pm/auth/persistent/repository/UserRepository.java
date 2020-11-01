package com.pm.auth.persistent.repository;

import java.util.Optional;

import com.pm.auth.persistent.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String> {
  public Optional<User> findByUsername(String username);

  public User findByEmail(String email);

  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);
}