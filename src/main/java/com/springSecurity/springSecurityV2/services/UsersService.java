package com.springSecurity.springSecurityV2.services;

import com.springSecurity.springSecurityV2.Repository.UserRepository;
import com.springSecurity.springSecurityV2.controllers.AthenticationRequest;
import com.springSecurity.springSecurityV2.controllers.AuthenticationResponse;
import com.springSecurity.springSecurityV2.controllers.RegisterRequest;
import com.springSecurity.springSecurityV2.models.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
private  final  JwtService jwtService;
private  final AuthenticationManager authenticationManager;

    public AuthenticationResponse regitser(RegisterRequest request) {
var user = Users.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();

userRepository.save(user);

//We create now a token

        var token=jwtService.generateNewToken(user);

        return AuthenticationResponse.builder()
                .token(token).build();


    }

    public AuthenticationResponse auhenticate(AthenticationRequest request) {


//        ! we use the authentication manager to validate the email and password
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              ))  ;

        //        to this point the user is authenticated

//               if not we need to search the user to the data base and authenticate

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token=jwtService.generateNewToken(user);

        return AuthenticationResponse.builder()
                .token(token).build();




    }
}
