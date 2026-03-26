package com.group9.fitnesstracker.config;

import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    public OAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("Custom OAuth2UserService.loadUser called!");
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");

        Optional<User> existingUser = userService.getUserByUsername(email);

        if (existingUser.isEmpty()) {
            User newUser = new User(email, false);
            userService.saveUser(newUser.getUsername(), newUser.getIsAdmin());
        } else {
            List<GrantedAuthority> authorities = new ArrayList<>(oauth2User.getAuthorities());

            if (existingUser.get().getIsAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new OAuth2User() {
                @Override
                public Map<String, Object> getAttributes() {
                    return oauth2User.getAttributes();
                }

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return authorities;
                }

                @Override
                public String getName() {
                    return oauth2User.getName();
                }
            };
        }
        return oauth2User;
    }
}