package com.springSecurity.springSecurityV2.controllers;

import com.springSecurity.springSecurityV2.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest {


    private String name;
    private  String email;
    private  String password;
    private Role role;
}
