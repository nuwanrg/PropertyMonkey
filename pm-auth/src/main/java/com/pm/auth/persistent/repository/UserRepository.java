package com.pm.auth.persistent.repository;

import java.util.Optional;

import com.pm.common.persistence.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, String> {
  public Optional<User> findByUsername(String username);

  public User findByEmail(String email);

  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);
}