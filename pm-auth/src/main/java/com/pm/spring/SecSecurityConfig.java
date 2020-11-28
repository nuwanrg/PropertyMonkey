/*
package com.pm.spring;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class SecSecurityConfig {
    @Bean(name="GeoIPCountry")
    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception {
        //System.getProperty("user.dir");
        final File resource = new File("/Users/nuwan/git/propertymonkey/pm-auth/src/main/resources/maxmind/GeoLite2-Country.mmdb");
        return new DatabaseReader.Builder(resource).build();
    }
}
*/
