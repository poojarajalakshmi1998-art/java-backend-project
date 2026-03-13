package com.pooja.auth_service.repository;


import com.pooja.auth_service.entity.Users;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);

    boolean existsByUsername(@NotBlank(message="Name is required") String username);
}
