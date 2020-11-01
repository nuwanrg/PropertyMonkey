package com.pm.auth.persistent.repository;

import java.util.Optional;

import com.pm.auth.persistent.model.ERole;
import com.pm.auth.persistent.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}