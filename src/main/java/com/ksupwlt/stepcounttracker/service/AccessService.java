package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public AccessService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUsernameAvailable(String username){
        return userRepository.findByUsername(username) == null;
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        User newUser = userRepository.save(user);
        return newUser;
    }

    public boolean validateUser(String username, String password){
        var matchingUser = userRepository.findByUsername(username);
        if(matchingUser == null){
            return false;
        } else {
            if(passwordEncoder.matches(password, matchingUser.getPassword())){
                return true;
            } else {
                return false;
            }
        }
    }

    // Used for test purposes
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userRepository.save(new User("anegrona",passwordEncoder.encode("password"),"ROLE_USER"));
            userRepository.save(new User("afulle62",passwordEncoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
            userRepository.save(new User("dsanfor5",passwordEncoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
            userRepository.save(new User("user",passwordEncoder.encode("password"),"ROLE_USER"));
            userRepository.save(new User("admin",passwordEncoder.encode("password"),"ROLE_USER,ROLE_ADMIN"));
        };
    }
}
