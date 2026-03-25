package com.group9.fitnesstracker.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // This line basically asks if http can go and access the endpoints
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Does not need to be authenticated when logging in, so we can permit all
                        .requestMatchers("/oauth2/**", "/login/**", "/error").permitAll()
                        // Stops non admin users from going to admin routes
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // Handles the first line above if not logged in or error
                .exceptionHandling(ex -> ex
                        .defaultAuthenticationEntryPointFor(
                                (request, response, authException) -> {
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter().write("{\"error\":\"unauthorized\"}");
                                },
                                request -> request.getRequestURI().startsWith("/user/")
                        )
                )

                // enable Google OAuth login (creates session)
                .oauth2Login(oAuth2Login -> {
                    oAuth2Login.defaultSuccessUrl("http://localhost:5173/dashboard");
                    oAuth2Login.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService));
                })
                .cors(cors -> {
                        cors.configurationSource(getUrlBasedCorsConfigurationSource());
                });

        return http.build();
    }
    private static UrlBasedCorsConfigurationSource getUrlBasedCorsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS","PATCH"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}