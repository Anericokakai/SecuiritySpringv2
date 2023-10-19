package com.springSecurity.springSecurityV2.Services;

import com.springSecurity.springSecurityV2.Repository.UsersRepoistory;
import com.springSecurity.springSecurityV2.Security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JpaUserDetailsService  implements UserDetailsService {

    private  final UsersRepoistory usersRepoistory;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        var u=usersRepoistory.findByEmail(username);

      return   u.map(SecurityUser::new).orElseThrow(()->new UsernameNotFoundException("Username not found" +username));
    }
}
