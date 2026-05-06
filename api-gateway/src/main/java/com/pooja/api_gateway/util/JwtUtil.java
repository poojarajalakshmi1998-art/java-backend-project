package com.pooja.api_gateway.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;


    public String extractUsername(String token){

        return Jwts.parser().
                setSigningKey(secretKey.getBytes()).
                build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}


