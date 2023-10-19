package com.springSecurity.springSecurityV2.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/home")

    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok().body("say hello");
    }
}
