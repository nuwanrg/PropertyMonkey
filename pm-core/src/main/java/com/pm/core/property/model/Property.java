package com.pm.core.property.model;

import com.pm.auth.jwt.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Property {
    @Id
    private String id;
    private String location;
    private String category; //buy or rent
    private String title;
    private String address;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private String type;
    private String tenure;
    private int floorSize;
    private String developer;
    private String furnishing;
    private int completedYear;
    private LocalDateTime createDate;
    private LocalDateTime expiryDate;
    private String ref_id;

    @DBRef
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
