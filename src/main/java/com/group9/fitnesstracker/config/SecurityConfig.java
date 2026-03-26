package com.group9.fitnesstracker.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;

    public SecurityConfig(OAuth2UserService oAuth2UserService) {
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(authorize -> authorize
                        // allow landing + auth endpoints
//                        .requestMatchers("/", "/error", "/oauth2/**", "/login/**").permitAll()
                        // everything else requires auth (either session login or JWT depending on request)
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/", "/error", "/oauth2/**", "/login/**").permitAll()
                                .requestMatchers("/user/currentUser", "/api/workouts/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                // enable Google OAuth login (creates session)
                .exceptionHandling(ex -> ex
                        .defaultAuthenticationEntryPointFor(
                                (request, response, authException) -> {
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"error\":\"unauthorized\"}");
                                },
                                request -> request.getRequestURI().startsWith("/user/")
                                        || request.getRequestURI().startsWith("/api/")
                        )
                )

                //.oauth2Login(Customizer.withDefaults());
                // keep JWT support for endpoints that are called with Authorization: Bearer <token>
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

                 .oauth2Login(oAuth2Login -> {
            oAuth2Login.defaultSuccessUrl("http://localhost:5173/dashboard");
            oAuth2Login.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService));
        })
                .cors(cors -> cors.configurationSource((CorsConfigurationSource) corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}