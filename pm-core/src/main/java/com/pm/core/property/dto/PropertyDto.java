package com.pm.core.property.dto;

import com.pm.common.persistence.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
public class PropertyDto {

    private String id;
    private String location;
    private String category;
    private String title;
    private String address;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private String type;
    private LocalDateTime createDate;
    private LocalDateTime expiryDate;
    private String userId;

}
