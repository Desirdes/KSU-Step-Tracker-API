package com.ksupwlt.stepcounttracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    	@Bean
	public UserDetailsService userDetailService() {

		var user = User.withUsername("user")
			.password("{noop}password")
			.roles("USER")
			.build();

		var admin = User.withUsername("admin")
				.password("{noop}password")
				.roles("ADMIN", "USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> {
                    auth
                            //.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .mvcMatchers("/api/v1/users/**").hasRole("USER")
                            .mvcMatchers("/api/v1/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                });

        http.sessionManagement(
                session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
        );
        http.httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
}
