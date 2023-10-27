package com.springSecurity.springSecurityV2.CustomAuthentications.configs.SecurityFIlter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
private  final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                   @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {


//        now we can create our custom authentication here
// 1 create an authentication object which is not
        String key=String.valueOf(request.getHeader("key"));

        CustomAuthentication customAuth=new CustomAuthentication(false,key);

        var auth=customAuthenticationManager.authenticate(customAuth);

        filterChain.doFilter(request,response);
    }
}
