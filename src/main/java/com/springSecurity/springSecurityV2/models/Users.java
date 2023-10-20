package com.springSecurity.springSecurityV2.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")

@Getter
@Setter
public class Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;
@Column(name = "name")
    private String username;
    private String email;
    private  String password;
    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",joinColumns =@JoinColumn(name ="user_id" ) ,inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;


}
