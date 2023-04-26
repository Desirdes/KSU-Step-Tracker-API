package com.ksupwlt.stepcounttracker.config;

import com.ksupwlt.stepcounttracker.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Creation of initial users for sample
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().and().authorizeHttpRequests(
                auth -> {
                    auth
                            //.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .mvcMatchers("/access/**").permitAll()
                            .mvcMatchers("/api/v1/users/**").hasRole("USER")
                            .mvcMatchers("/api/v1/admin/**").hasRole("ADMIN")
                            .mvcMatchers("/swagger-ui/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                });

        http.sessionManagement(
                session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
        );

        http.httpBasic();
        http.csrf().disable();
        http.userDetailsService(userService);
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

}
