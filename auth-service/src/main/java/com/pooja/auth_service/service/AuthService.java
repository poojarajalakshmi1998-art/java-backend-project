package com.pooja.auth_service.service;


import com.pooja.auth_service.Exception.InvalidUserNameException;
import com.pooja.auth_service.dto.LoginRequest;
import com.pooja.auth_service.dto.RegisterRequest;
import com.pooja.auth_service.entity.Users;
import com.pooja.auth_service.enums.Role;
import com.pooja.auth_service.repository.UserRepository;
import com.pooja.auth_service.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest request)
    {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
    Users user= new Users();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.valueOf(request.getRole()));


    userRepository.save(user);

    }
/* instead of the below method , I used UserDetailService through AuthenticationManager
    public String login(LoginRequest request)
    {
      Users users=userRepository.
              findByUsername(request.getUsername()).orElseThrow(() -> new InvalidUserNameException("User not found"));

      if(!(passwordEncoder.matches(request.getPassword(), users.getPassword())))
      {
          throw new RuntimeException("Wrong password");
      }
      return jwtUtil.generateToken(users.getUsername(),users.getRole().toString());




    }
*/


}
