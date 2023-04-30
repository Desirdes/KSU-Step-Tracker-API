package com.ksupwlt.stepcounttracker.service;

import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.entity.UserSecurity;
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

    public boolean userIsPerson(UserSecurity loggedInUser, Long personID){
        if (loggedInUser.getPersonID() == personID){
            return true;
        }
        return false;
    }

    public static class passwordResetData{
        private String username;
        private String password;

        public passwordResetData(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
    }
    public boolean resetUserPassword(passwordResetData data){
        // Find user, and check it has password reset flag
        // If it is found, reset password with new one provided
        User userFound = userRepository.findByUsername(data.getUsername());
        if (userFound == null){
            return false;
        } else if(userFound.getResetPassword()){
            userFound.setPassword(passwordEncoder.encode(data.getPassword()));
            userFound.setResetPassword(false);
            userRepository.save(userFound);
            return true;
        }
        return false;
    }
}
