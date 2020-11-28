package com.pm.core.property.model;


import com.pm.common.persistence.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Property {
    @Id
    private String id;
    @Indexed
    private String location;
    @Indexed
    private String category; //buy or rent
    @Indexed
    private String title;
    private String address;
    @Indexed
    private double price;
    @Indexed
    private int bedrooms;
    private int bathrooms;
    @Indexed
    private String type;
    private String tenure;
    private int floorSize;
    private String developer;
    private String furnishing;
    private int completedYear;
    private LocalDateTime createDate;
    private LocalDateTime expiryDate;

    @Transient
    private String username;

    @DBRef
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
