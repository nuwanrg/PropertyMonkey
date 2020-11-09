package com.pm.auth.persistent.repository;

import java.util.Optional;

import com.pm.common.persistence.model.ERole;
import com.pm.common.persistence.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
  public Role findByStrRole(String strRole);

}