package com.pm.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "pm.admin")
@Configuration("adminProperties")
public class AdminProperties {
    private String username;
    private String password;
    private String email;
}
