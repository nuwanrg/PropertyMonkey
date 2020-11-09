package com.pm.spring;

import com.pm.auth.persistent.repository.RoleRepository;
import com.pm.common.persistence.model.ERole;
import com.pm.common.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    @Autowired
    private RoleRepository roleRepository;

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


    }

    @Transactional
    Role createRoleIfNotFound( ERole name) {

        Role role = roleRepository.findByStrRole(name.name());
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

}
