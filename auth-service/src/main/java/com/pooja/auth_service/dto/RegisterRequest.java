package com.pooja.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class RegisterRequest {
    @NotBlank(message="Name is required")
    private String username;
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 8 characters with letters and numbers"
    )
    private String password;
    private String role;
}
