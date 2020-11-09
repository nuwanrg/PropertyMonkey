package com.pm.auth.persistent.repository;
import com.pm.common.persistence.model.UserLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserLocationRepository extends MongoRepository<UserLocation, String> {
}
