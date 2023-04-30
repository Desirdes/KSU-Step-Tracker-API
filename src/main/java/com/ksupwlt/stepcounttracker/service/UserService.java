package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.entity.UserSecurity;
import com.ksupwlt.stepcounttracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserSecurity(user);
    }

    public String flagUserResetPassword(String username){
        // Flag user only if it's founds
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        } else {
            // Generate a random string for use as a temp password while waiting for user reset
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 8) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String tempPassword = salt.toString();
            user.setResetPassword(true);
            user.setPassword(passwordEncoder.encode(tempPassword));
            userRepository.save(user);
            return tempPassword;
        }
    }
}
