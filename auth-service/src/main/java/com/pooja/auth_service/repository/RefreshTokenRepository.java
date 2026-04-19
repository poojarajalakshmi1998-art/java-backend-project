package com.pooja.auth_service.repository;

import com.pooja.auth_service.entity.RefreshToken;
import com.pooja.auth_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String RefreshToken);
    void deleteByRefreshToken(String RefreshToken);

    Optional<RefreshToken> findByUser(Users user);
}
