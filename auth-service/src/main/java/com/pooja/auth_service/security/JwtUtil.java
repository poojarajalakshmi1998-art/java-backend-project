package com.pooja.auth_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component

public class JwtUtil {

    private static final String secret_key="mysecretkeymysecretkeymysecretkey";

    public String generateToken(String username,String role){

      return  Jwts.builder().setSubject(username).
                setIssuedAt(new Date()).
              setExpiration(new Date(System.currentTimeMillis() +
                      //86400000 -- for 24hrs
                      15*60*1000 //---for 1 mins

              )).claim("role",role)
              .signWith(Keys.hmacShaKeyFor(secret_key.getBytes()), SignatureAlgorithm.HS256).compact();

    }

   public String extractUsername(String token){

       return Jwts.parserBuilder().
                setSigningKey(secret_key.getBytes()).
                build().parseClaimsJws(token).getBody().getSubject();
   }


   public String extractrole(String Token){
      return  Jwts.parserBuilder().setSigningKey(secret_key.getBytes()).build().parseClaimsJws(Token).getBody().get("role", String.class);
   }
   public boolean validateToken(String token){

       try {
           Jwts.parserBuilder().setSigningKey(secret_key.getBytes()).build().parseClaimsJws(token);
           return true;
       }
       catch(Exception e){
           return false;
       }

   }

}


