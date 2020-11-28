package com.pm.spring;

import com.pm.auth.persistent.repository.RoleRepository;
import com.pm.auth.persistent.repository.UserRepository;
import com.pm.auth.services.MailService;
import com.pm.common.persistence.model.ERole;
import com.pm.common.persistence.model.Role;
import com.pm.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        ERole.stream().forEach(a ->

                {
                    createRoleIfNotFound(a);
                }
        );

        createAdminUser();
        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(ERole name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    public void createAdminUser() {

        if (userRepository.findByEmail(adminProperties.getEmail()) == null) {
            User user = new User();
            user.setUsername(adminProperties.getUsername());
            user.setPassword(encoder.encode(adminProperties.getPassword()));
            user.setEmail(adminProperties.getEmail()); //TODO get admin password from properties
            Set<Role> roles = new HashSet<Role>();
            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN));
            user.setRoles(roles);
            userRepository.save(user);

            mailService.sendCreateAdminEmail(user.getEmail());
        }
    }

}
