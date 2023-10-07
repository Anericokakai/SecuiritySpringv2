package com.springSecurity.springSecurityV2.SecuirtyFilter;

import com.springSecurity.springSecurityV2.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//! implementing the filter on every request that comes to the server
@Component
@RequiredArgsConstructor
public class JwtAuthentication  extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
          @NonNull  FilterChain filterChain) throws ServletException, IOException {


        String userName;
        String token;

//        ! acces the header from the requst
        String header=request.getHeader("Authorization");


//! handle if there is no header or the header does not start with bearer


        if(header==null||!header.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        //     ! substring the token from the header

        token=header.substring(7);

//        create a method to extract tokens and validate tokens

userName= jwtService.extractUserName(token);

if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){

//    overide the userDetails service at the app configuration
    UserDetails userDetails=this.userDetailsService.loadUserByUsername(userName);

    if(jwtService.isTokenValid(token,userDetails)){
//         update the  security context holder

        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );


//        set the new value  of the auth token to be build from the requests

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
//        !update the security context
SecurityContextHolder.getContext().setAuthentication(authToken);

    }
}
filterChain.doFilter(request,response);






    }
}
