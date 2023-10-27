package com.springSecurity.springSecurityV2.CustomAuthentications.configs.SecurityFIlter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                   @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {


        filterChain.doFilter(request,response);
    }
}
