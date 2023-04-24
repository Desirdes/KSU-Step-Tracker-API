package com.ksupwlt.stepcounttracker.controller.prototype;

import com.ksupwlt.stepcounttracker.entity.Person;
import com.ksupwlt.stepcounttracker.repository.UserRepository;
import com.ksupwlt.stepcounttracker.service.AccessService;
import com.ksupwlt.stepcounttracker.entity.User;
import com.ksupwlt.stepcounttracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/access")
public class AuthenticationController {

    @Autowired
    private AccessService accessService;
    @Autowired
    private PersonService personService;
    @Autowired
    private UserRepository userRepository;

    private static class signupUserData{
        private String username;
        private String password;
        private String email;
        private String full_name;

        public signupUserData(String username, String password, String email, String full_name) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.full_name = full_name;
        }

        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
        public String getEmail() {
            return email;
        }
        public String getFullName() {
            return full_name;
        }
    }
    @PostMapping("/signup")
    public ResponseEntity signupUser(@RequestBody signupUserData userData){
        if(!accessService.isUsernameAvailable(userData.getUsername())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken.");
        } else {
            // Create a 'person' entry for this user to store additional data
            Person tempPerson = new Person();
            tempPerson.setUsername(userData.getUsername());
            tempPerson.setFull_name(userData.getFullName());
            tempPerson.setEmail(userData.getEmail());
            Person newPerson = personService.createPerson(tempPerson);

            // Create a 'user' entry for this new user
            User tempUser = new User();
            tempUser.setUsername(userData.getUsername());
            tempUser.setPassword(userData.getPassword());
            tempUser.setPersonId(newPerson.getId());
            User newUser = accessService.createUser(tempUser);

            // Clear password for returned data
            newUser.setPassword("");
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user){
        if(accessService.validateUser(user.getUsername(), user.getPassword())){
            var validatedUser = userRepository.findByUsername(user.getUsername());
            // Clear password for returned data
            validatedUser.setPassword("");
            return ResponseEntity.status(HttpStatus.OK).body(validatedUser);
        } else {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Invalid.");
        }
    }

    @PostMapping("/password-reset")
    public ResponseEntity resetPassword(@RequestBody AccessService.passwordResetData data){
        // Endpoint will work only for users that have been flagged by admin
        if(accessService.resetUserPassword(data)){
            return ResponseEntity.noContent().build();
        }else {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
