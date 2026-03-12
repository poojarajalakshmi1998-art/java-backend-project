package com.pooja.auth_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

@Autowired
private JwtUtil jwtutil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)throws ServletException, IOException

    {

String authHeader=request.getHeader("Authorization");
if(authHeader== null || !authHeader.startsWith("Bearer "))
{
    filterchain.doFilter(request,response);
    return;

}
try {
    String Token = authHeader.substring(7);
    String username = jwtutil.extractUsername(Token);
    String role = jwtutil.extractrole(Token);
    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtutil.validateToken(Token)) {
        UsernamePasswordAuthenticationToken Authtoken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(Authtoken);
    }
}
catch(Exception e)
{
    // Token expired or invalid
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write("Invalid or expired JWT token");
    return;
}

        filterchain.doFilter(request, response);
    }
}


