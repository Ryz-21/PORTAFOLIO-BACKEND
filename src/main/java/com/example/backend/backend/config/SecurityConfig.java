package com.example.backend.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll());

        return http.build();
    }

@Bean
public CorsConfigurationSource corsConfigurationSource() { 
    CorsConfiguration config = new CorsConfiguration();

    // Usamos el comodín para probar y descartar errores de URL
    config.setAllowedOriginPatterns(List.of("*")); 

    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
    config.setAllowedHeaders(List.of("Content-Type", "Authorization", "X-Requested-With", "Accept"));
    
    // IMPORTANTE: Si usas "*" en OriginPatterns, a veces 'setAllowCredentials(true)' 
    // puede dar problemas en navegadores muy estrictos. 
    // Si sigue fallando, prueba cambiando true por false.
    config.setAllowCredentials(true); 
    
    config.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return source;
}
}