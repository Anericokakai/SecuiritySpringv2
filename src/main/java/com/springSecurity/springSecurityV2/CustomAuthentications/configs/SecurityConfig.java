package com.springSecurity.springSecurityV2.CustomAuthentications.configs;

import com.springSecurity.springSecurityV2.CustomAuthentications.configs.SecurityFIlter.CustomAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
private  final CustomAuthenticationFilter customAuthenticationFilter;
@Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

   return http.addFilterAt(customAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
           .authorizeHttpRequests(req->req
                   .anyRequest()
                   .authenticated())
            .build();
}



}
