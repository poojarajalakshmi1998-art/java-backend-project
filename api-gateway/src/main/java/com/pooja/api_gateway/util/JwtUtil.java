package com.pooja.api_gateway.util;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String secret_key="mysecretkeymysecretkeymysecretkey";

    public String extractUsername(String token){

        return Jwts.parser().
                setSigningKey(secret_key.getBytes()).
                build().parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){

        try {
            Jwts.parser().setSigningKey(secret_key.getBytes()).build().parseClaimsJws(token);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}


