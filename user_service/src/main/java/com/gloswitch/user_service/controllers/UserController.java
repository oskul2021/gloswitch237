package com.gloswitch.user_service.controllers;

import com.gloswitch.user_service.models.*;
import com.gloswitch.user_service.repositories.UserRepository;
import com.gloswitch.user_service.service.Notification;
import com.gloswitch.user_service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    Notification notification;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public String test(){
        return "All thing is fine";
    }

    @PostMapping ("/create_user")
    public HashMap<String,Object> createUser(@RequestBody Map<String,String> body){
        HashMap<String,Object> map = new HashMap<>();
        User user = new User();
        user.setEmail(body.get("email"));
        user.setMobile(body.get("mobile"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        user.setFirstname(body.get("firstname"));
        user.setCountry(body.get("country"));
        Role userRole = new Role();
        userRole.setLibelle(Roletype.USER);
        user.setRole(userRole);
        User saved = userRepository.save(user);
        Validation validation = new Validation();
        validation.setUser(saved);
        Validation savedValidation = validationService.save(validation);
        notification.send(savedValidation);
        map.put("msg","user created successfully");
        return map;
    }

    @GetMapping("/validate/{code}")
    public HashMap<String,Object> validate(@PathVariable("code") String code){
        return validationService.validate(code);
    }

    @PostMapping("/signin")
    public Map<String,String> signin(@RequestBody AuthenticationDTO authentication){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentication.username(), authentication.password()));
        System.out.println("resutat :"+authenticate.isAuthenticated());
        return null;
    }
}
