package com.springSecurity.springSecurityV2.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Log4j2
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager users= new InMemoryUserDetailsManager();

//        ! create users
        var user1=User.builder()
                .username("rico")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        var user2=User.builder()
                .username("baddy")
                .roles("ADMIN")
                .password(passwordEncoder().encode("1235"))
                .build();
        users.createUser(user1);
        users.createUser(user2);
        return  users;
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> succsessLogger(){
        return  event -> {
            log.info("success:{}",event.getAuthentication());
        };
    }

@Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
}
}
