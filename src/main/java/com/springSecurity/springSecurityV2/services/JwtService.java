package com.springSecurity.springSecurityV2.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {

    private  final String SECRETE_KEY ="5dA8B3F1eC7a6D9f2E0b8A5c7D4eF1a7B6d9C8E5F3a2B0c8A1D7e4F2" ;


    //    !method to acces the username from the token which in our case is the email
    public  String extractUserName(String token) {
        return  extractSingleClaim( token,Claims::getSubject);
    }

//    ! method to extract a single claim from the token

    public  <T> T extractSingleClaim(String token, Function<Claims,T> claimsResolver) {

//! extract first all claims from the extraxt all claims
        final Claims claims=extractAllClaims(token);

        return   claimsResolver.apply(claims);
    }

//    ! create a method to extract all the claims from the token

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
    



//    ! method to create anew  with extra claims

    public String generateNewToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails){

     return   Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

//    ! generate a new key without the aany extra claims

    public  String  generateNewToken(UserDetails userDetails){
        return generateNewToken(new HashMap<>(),userDetails);


    }

//    VALIDATE THE TOKEN AND CHECK IF IT HAS NOT EXPIRED

    public  boolean isTokenValid(String token,UserDetails userDetails){

        String email= extractUserName(token);
        return (email.equals(userDetails.getUsername()))&& !TokenIsExpired(token);

    }

    private boolean TokenIsExpired(String token) {

        return extractSingleClaim(token,Claims::getExpiration).before(new Date());
    }

}
