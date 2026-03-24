package com.group9.fitnesstracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //will enable CORS so the browser can call backend from localhost
                .cors(Customizer.withDefaults())
                //for dev, this avoids csrf blocking
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))

                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/", "/error", "/oauth2/**", "/login/**").permitAll()
                                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/user/currentUser", "/api/workouts/**").permitAll()
                        // allow landing + auth endpoints
//                        .requestMatchers("/", "/error", "/oauth2/**", "/login/**").permitAll()
                        // everything else requires auth (either session login or JWT depending on request)
                        .anyRequest().authenticated()
                )
                // enable Google OAuth login (creates session)
                .oauth2Login(Customizer.withDefaults());
                // keep JWT support for endpoints that are called with Authorization: Bearer <token>
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}