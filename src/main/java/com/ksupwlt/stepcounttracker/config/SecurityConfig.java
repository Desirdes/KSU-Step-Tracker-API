package com.ksupwlt.stepcounttracker.config;

import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.repository.UserRepository;
import com.ksupwlt.stepcounttracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Autowired
    private UserService userService;

//    @Bean
//	public UserDetailsService userDetailService(DataSource dataSource) {
//
//		var user = User.withUsername("user")
//			.password("password")
//                .passwordEncoder(str -> passwordEncoder().encode(str))
//			.roles("USER")
//			.build();
//
//		var admin = User.withUsername("admin")
//				.password("password")
//                .passwordEncoder(str -> passwordEncoder().encode(str))
//				.roles("ADMIN", "USER")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}

    @Bean
    CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            users.save(new com.ksupwlt.stepcounttracker.entity.User("user",encoder.encode("password"),"ROLE_USER"));
            users.save(new User("admin",encoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
        };
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
        http.userDetailsService(userService);
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
