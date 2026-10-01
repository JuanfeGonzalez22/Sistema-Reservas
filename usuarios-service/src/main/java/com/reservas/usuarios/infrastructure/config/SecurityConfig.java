package com.reservas.usuarios.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder(@Value("${spring.security.oauth2.resourceserver.jwt.secret-key}") String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        MacAlgorithm algorithm = macAlgorithmFor(keyBytes.length);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, algorithm.getName());
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(algorithm).build();
    }

    private MacAlgorithm macAlgorithmFor(int keyByteLength) {
        int bits = keyByteLength * 8;
        if (bits >= 512) return MacAlgorithm.HS512;
        if (bits >= 384) return MacAlgorithm.HS384;
        if (bits >= 256) return MacAlgorithm.HS256;
        throw new IllegalStateException("app.jwt.secret / JWT_SECRET debe tener al menos 256 bits (32 caracteres)");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));

        return http.build();
    }
}