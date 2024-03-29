package com.pm;

import com.pm.auth.services.UserDetailsServiceImpl;
import com.pm.auth.persistent.repository.UserRepository;
import com.pm.common.persistence.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class UserDetailsServiceImplIntegrationTest {

    @MockBean
    UserRepository userRepository;

/*    @TestConfiguration
    static class UserDetailsServiceImplTestContextConfiguration{
        @Bean
        public UserDetailsService userDetailsService(){
            return new UserDetailsServiceImpl();
        }
    }*/

    @BeforeEach
    public void prepare(){
        System.out.println("Preparing for running service layer integration testing");
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setEmail("newuser@gmail.com");

        Mockito.when(userRepository.findByUsername(newUser.getUsername())).thenReturn(Optional.of(newUser));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound(){
        String name = "testUser";
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()){
            assertThat(user.get().getUsername()).isEqualTo( name);
        }

        System.out.println("Running service layer integration testing");
    }
}
