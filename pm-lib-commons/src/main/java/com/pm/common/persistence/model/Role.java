package com.pm.common.persistence.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id;

    private ERole name;

    //private String strRole;

    public Role() {

    }

    public Role(ERole name) {

        this.name = name;
        //this.strRole = name.toString();
    }


}