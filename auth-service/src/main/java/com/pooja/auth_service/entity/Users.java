package com.pooja.auth_service.entity;


import com.pooja.auth_service.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
