package com.example.mini_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Bean
    public PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/domains/**","/employers/**","/profiles/**","/structures/**","/users/**").hasRole("ADMIN")
                        .requestMatchers("/statistics/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/trainers/**", "/trainings/**", "/participants/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(
                                "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", 
                                "/", "/login", "/resources/**", "/static/**", "/css/**", "/js/**", "/picture/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/home", true))
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }
}
