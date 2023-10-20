package com.springSecurity.springSecurityV2.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/home")

    public ResponseEntity<?> sayHello(){
//        var u= SecurityContextHolder.getContext().getAuthentication();
//        u.getAuthorities().forEach(System.out::println);
        return ResponseEntity.ok().body("say hello");
    }
}
