//package com.springSecurity.springSecurityV2.Configs;
//
//
//import com.springSecurity.springSecurityV2.Services.JpaUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class WebSecurityConfig {
//
////    @Bean
////    public UserDetailsService userDetailsService(){
////
////
//
////! overide the default spring user and pasord
////        var usd= new InMemoryUserDetailsManager();
////
////var u1 = User.withUsername("anerico"
////        ).password("1234"
////        ).authorities("ADMIN")
////        .build();
////usd.createUser(u1);
////return usd;
////
////    }
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
