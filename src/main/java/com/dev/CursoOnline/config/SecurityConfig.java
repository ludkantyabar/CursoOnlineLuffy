package com.dev.CursoOnline.config;

import com.dev.CursoOnline.security.JwtAuthenticationFilter;
import com.dev.CursoOnline.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil) {
        return new JwtAuthenticationFilter(jwtUtil);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
<<<<<<< HEAD
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()  // Permite todo (solo para desarrollo)
=======
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                // ADMIN puede acceder a todo
                .requestMatchers("/usuarios/**", "/roles/**").hasRole("ADMIN")
                // DOCENTE puede agregar y eliminar cursos (POST, DELETE)
                .requestMatchers(HttpMethod.POST, "/cursos/**").hasAnyRole("ADMIN", "DOCENTE")
                .requestMatchers(HttpMethod.DELETE, "/cursos/**").hasAnyRole("ADMIN", "DOCENTE")
                // Solo ADMIN y DOCENTE pueden ver cursos (GET)
                .requestMatchers(HttpMethod.GET, "/cursos/**").hasAnyRole("ADMIN", "DOCENTE")
                // ESTUDIANTE puede registrar curso, ver y eliminar sus registros
                .requestMatchers(HttpMethod.POST, "/cursoregistros/**").hasAnyRole("ADMIN", "ESTUDIANTE")
                .requestMatchers(HttpMethod.GET, "/cursoregistros/**").hasAnyRole("ADMIN", "ESTUDIANTE")
                .requestMatchers(HttpMethod.DELETE, "/cursoregistros/**").hasAnyRole("ADMIN", "ESTUDIANTE")
                .anyRequest().authenticated()
>>>>>>> 2979b9cd88b6497800b6ca1bda07208f13893570
            )
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}