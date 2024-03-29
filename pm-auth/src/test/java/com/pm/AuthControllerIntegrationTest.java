package com.pm;

import com.pm.auth.jwt.JwtUtils;
import com.pm.auth.services.UserSignUpService;
import com.pm.auth.persistent.repository.RoleRepository;
import com.pm.auth.persistent.repository.UserRepository;
import com.pm.common.persistence.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PMAuthMain.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    PasswordEncoder encoder;

    @MockBean
    JwtUtils jwtUtils;

    @MockBean
    private UserSignUpService userSignUpService;


    @Test
    public void whenValidInput_ThenCreateUser() throws IOException, Exception{
        User user = new User("test1"  , "test1@gmail.com" , "test123");
        mvc.perform(post("/auth/signup").contentType(MediaType.APPLICATION_JSON).content( JsonUtil.toJson(user)));

        System.out.println("OK");
        List<User> users = userRepository.findAll();
        System.out.println("OK1");

        assertThat(users).extracting(User::getUsername).containsOnly("test1");
        System.out.println("Running AuthControllerIntegrationTest");
    }


}

