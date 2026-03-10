package com.pooja.auth_service.service;


import com.pooja.auth_service.dto.RegisterRequest;
import com.pooja.auth_service.entity.Users;
import com.pooja.auth_service.enums.Role;
import com.pooja.auth_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request)
    {
    Users user= new Users();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.ROLE_USER);
    userRepository.save(user);

    }
}
