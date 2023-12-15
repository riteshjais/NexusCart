package com.nexus.cart.auth;

import com.nexus.cart.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private Role role;
    private String email;
    private String password;
    private String phoneNumber;
}
