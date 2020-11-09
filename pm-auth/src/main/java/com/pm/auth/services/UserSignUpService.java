package com.pm.auth.services;

import com.maxmind.geoip2.DatabaseReader;
import com.pm.auth.exception.EmailAlreadyExistException;
import com.pm.auth.exception.UserAlreadyExistException;
import com.pm.auth.jwt.JwtUtils;
import com.pm.auth.jwt.payload.response.MessageResponse;
import com.pm.auth.persistent.repository.RoleRepository;
import com.pm.auth.persistent.repository.UserLocationRepository;
import com.pm.auth.persistent.repository.UserRepository;
import com.pm.common.persistence.model.ERole;
import com.pm.common.persistence.model.Role;
import com.pm.common.persistence.model.UserLocation;
import com.pm.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
@Component
public class UserSignUpService {
    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("GeoIPCountry")
    private DatabaseReader databaseReader;

    @Autowired
    private UserLocationRepository userLocationRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    public MessageResponse singUp(final User user){

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException("Username "+  user.getUsername() +" is already exists.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("Email "+  user.getEmail() +" is already exists.");
        }

        // Create new user's account
        String password = encoder.encode(user.getPassword());
        User newUser = new User(user.getUsername(), user.getEmail(), password );

        String strRole = user.getStrRole();
        Role role;
        if (strRole == null )
        {
            role = roleRepository.findByStrRole(ERole.ROLE_USER.name());

        }else {
            role = roleRepository.findByStrRole(strRole);
        }



        newUser.setRole(role);
        userRepository.save(newUser);

        //TODO FOR FUTURE WORKS-DO NOT REMOVE THIS
        //userService.addUserLocation(user, getClientIP(request));

        return new MessageResponse("User registered successfully!");
    }

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