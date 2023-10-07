package com.springSecurity.springSecurityV2.controllers;

import com.springSecurity.springSecurityV2.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/all")
@RequiredArgsConstructor
public class UserController {


    private  final UsersService usersService;
    @PostMapping("/newUser")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED.value()).body(usersService.regitser(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AthenticationRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED.value()).body(usersService.auhenticate(request));
    }





}
