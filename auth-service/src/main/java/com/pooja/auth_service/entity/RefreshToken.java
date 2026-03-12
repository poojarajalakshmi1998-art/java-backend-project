package com.pooja.auth_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="refresh_token")
public class RefreshToken {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
    private Long id;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name="expiry_Date")
    private Instant expiryDate;
    @OneToOne
    @JoinColumn( name= "user_id")
     private Users user;
}


