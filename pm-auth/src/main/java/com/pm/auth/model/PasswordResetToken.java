package com.pm.auth.model;

import com.pm.common.persistence.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Data
@Document(collection = "passwordresettoken")
public class PasswordResetToken {

    private static final int EXPIRATION=60*24;

    @Id
    private String id;

    @NotNull
    private String token;

    @DBRef
    private User user;

    private Date expiryDate;

    public PasswordResetToken(final String token) {
        super();
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetToken(final String token, final User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
