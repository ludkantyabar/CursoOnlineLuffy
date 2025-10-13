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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil) {
        return new JwtAuthenticationFilter(jwtUtil);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
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
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}