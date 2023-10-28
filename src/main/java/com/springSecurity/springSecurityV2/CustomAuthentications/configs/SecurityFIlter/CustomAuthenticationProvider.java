package com.springSecurity.springSecurityV2.CustomAuthentications.configs.SecurityFIlter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

    @Value("our.secret.key")
    private  String key;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication ca=(CustomAuthentication) authentication;
        String headerKey=ca.getKey();

        if(key.equals(headerKey)){

            return new CustomAuthentication(true,null);

        }
        throw  new BadCredentialsException("key is not a valid key");
    }

    @Override
    public boolean supports(Class<?> authentication) {
//   how does the auth Mager knows the Auth provider
        return CustomAuthenticationManager.class.equals(authentication);
    }
}
