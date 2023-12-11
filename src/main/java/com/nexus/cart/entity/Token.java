package com.nexus.cart.entity;

import com.nexus.cart.entity.enums.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "token")
    private String token;
    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    @Column(name = "expired")
    private boolean expired;
    @Column(name = "revoked")
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}