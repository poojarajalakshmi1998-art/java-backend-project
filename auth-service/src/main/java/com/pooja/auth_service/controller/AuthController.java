package com.pooja.auth_service.controller;

import com.pooja.auth_service.dto.AuthResponse;
import com.pooja.auth_service.dto.LoginRequest;
import com.pooja.auth_service.dto.RefreshTokenRequest;
import com.pooja.auth_service.dto.RegisterRequest;
import com.pooja.auth_service.entity.RefreshToken;
import com.pooja.auth_service.entity.Users;
import com.pooja.auth_service.repository.RefreshTokenRepository;
import com.pooja.auth_service.repository.UserRepository;
import com.pooja.auth_service.security.JwtUtil;
import com.pooja.auth_service.service.AuthService;
import com.pooja.auth_service.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    public AuthController(AuthService authService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");


    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
//the below line will call UserDetailsService and also will encode password using passwordencoder.matches(PasswordfromReqest,PasswordinDB) and will get username and roles from Db
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority();
        //  String    token = authService.login(loginRequest);
        String    accesstoken =  jwtUtil.generateToken(loginRequest.getUsername(),role);

        RefreshToken refreshtoken = refreshTokenService.createRefreshToken(loginRequest.getUsername());


       return new AuthResponse(accesstoken,refreshtoken.getRefreshToken());



    }
@GetMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request)
{

   RefreshToken refreshtoken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken()).orElseThrow(()-> new RuntimeException("Refresh token not found"));

    Users users=refreshtoken.getUser();
    String username=users.getUsername();
    String role=users.getRole().toString();
    if (refreshtoken.getExpiryDate().isBefore(Instant.now())) {
        throw new RuntimeException("Refresh token expired");
    }
    String    accesstoken =  jwtUtil.generateToken(username,role);


    return new AuthResponse(accesstoken,refreshtoken.getRefreshToken());


}
}
