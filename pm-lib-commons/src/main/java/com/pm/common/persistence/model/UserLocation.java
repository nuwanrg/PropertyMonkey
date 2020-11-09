package com.pm.common.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "userlocation")
public class UserLocation {
    @Id
    private Long id;

    private String country;

    private boolean enabled;

    @DBRef
    private User user;

    public UserLocation() {
        super();
        enabled = false;
    }

    public UserLocation(String country, User user) {
        super();
        this.country = country;
        this.user = user;
        enabled = false;
    }
}
