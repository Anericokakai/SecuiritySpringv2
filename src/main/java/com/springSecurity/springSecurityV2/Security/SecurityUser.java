package com.springSecurity.springSecurityV2.Security;

import com.springSecurity.springSecurityV2.models.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SecurityUser  implements UserDetails {

    private  final Users users;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return users.getAuthorities()
                .stream()
                .map(SecurityAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        System.out.println("password: "+users.getPassword());
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
