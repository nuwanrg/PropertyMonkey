package com.pm.auth.jwt.services;

import com.maxmind.geoip2.DatabaseReader;
import com.pm.auth.persistent.repository.UserLocationRepository;
import com.pm.common.persistence.model.UserLocation;
import com.pm.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
@Component
public class UserService {
    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("GeoIPCountry")
    private DatabaseReader databaseReader;

    @Autowired
    private UserLocationRepository userLocationRepository;

    public void addUserLocation(User user, String ip) {

        if(!isGeoIpLibEnabled()) {
            return;
        }

        try {
            final InetAddress ipAddress = InetAddress.getByName(ip);
            final String country = databaseReader.country(ipAddress)
                    .getCountry()
                    .getName();
            UserLocation loc = new UserLocation(country, user);
            loc.setEnabled(true);
            userLocationRepository.save(loc);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isGeoIpLibEnabled() {
        return Boolean.parseBoolean(env.getProperty("geo.ip.lib.enabled"));
    }
}

/*
Almost there …
Please check your email (coolnuwan@gmail.com)
to confirm your account.
If coolnuwan@gmail.com is not your email address, please go back and enter the correct one.

If you haven't received our email in 15 minutes, please check your spam folder.

Still can't find it? Try searching Gmail for in:all subject:(Confirm your account on Heroku)
 */