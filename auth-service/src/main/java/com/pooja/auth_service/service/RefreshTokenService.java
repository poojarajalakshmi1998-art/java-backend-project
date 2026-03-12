package com.pooja.auth_service.service;

import com.pooja.auth_service.Exception.InvalidUserNameException;
import com.pooja.auth_service.entity.RefreshToken;
import com.pooja.auth_service.entity.Users;
import com.pooja.auth_service.repository.RefreshTokenRepository;
import com.pooja.auth_service.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
private static final long RefreshTokenDuration= 7 * 24 * 60 * 60 * 1000;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String username)
    {

        Users user =userRepository.findByUsername(username).orElseThrow(()-> new InvalidUserNameException("User Name Not found"));
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(RefreshTokenDuration));
        return refreshTokenRepository.save(refreshToken);


    }

    public RefreshToken verifyexpiration  (RefreshToken refreshToken)
    {
if((refreshToken.getExpiryDate().compareTo(Instant.now()) <0))
        {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh Token Expired");
        }
        return refreshToken;

    }
}
