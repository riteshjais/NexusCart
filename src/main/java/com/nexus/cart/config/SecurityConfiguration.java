package com.nexus.cart.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.nexus.cart.entity.enums.Permission.*;
import static com.nexus.cart.entity.enums.Role.ADMIN;
import static com.nexus.cart.entity.enums.Role.CUSTOMER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Autowired
    private final JWTAuthenticationFilter jwtAuthFilter;
    @Autowired
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    private final LogoutService logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String adminUrl="/api/v1/admin/**";
        String customerUrl="/api/v1/users/**";
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "swagger-ui.html")
                .permitAll()
                .requestMatchers(adminUrl).hasRole(ADMIN.name())
                .requestMatchers(GET,adminUrl).hasAuthority(ADMIN_READ.name())
                .requestMatchers(POST,adminUrl).hasAuthority(ADMIN_CREATE.name())
                .requestMatchers(DELETE,adminUrl).hasAuthority(ADMIN_DELETE.name())
                .requestMatchers(PUT,adminUrl).hasAuthority(ADMIN_UPDATE.name())
                .requestMatchers(customerUrl).hasRole(CUSTOMER.name())
                .requestMatchers(GET,customerUrl).hasAuthority(CUSTOMER_READ.name())
                .requestMatchers(POST,customerUrl).hasAuthority(CUSTOMER_CREATE.name())
                .requestMatchers(DELETE,customerUrl).hasAuthority(CUSTOMER_DELETE.name())
                .requestMatchers(PUT,customerUrl).hasAuthority(CUSTOMER_UPDATE.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
        return http.build();
    }
}