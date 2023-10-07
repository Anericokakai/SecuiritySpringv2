package com.springSecurity.springSecurityV2.SecurityConfig;

import com.springSecurity.springSecurityV2.SecuirtyFilter.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityCongiguration {
    private final JwtAuthentication jwtAuthenticationFilter;
private  final AuthenticationProvider authenticationProvider;

    @Bean
 public   SecurityFilterChain  securityFilterChai(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req->req.requestMatchers("/api/v2/all/**")
                .permitAll()
                .anyRequest()
                .authenticated()

        )
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        ).authenticationProvider(authenticationProvider)


                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();

    }

}