package com.gloswitch.user_service;

import com.gloswitch.user_service.models.Validation;
import com.gloswitch.user_service.repositories.ValidationRepository;
import com.gloswitch.user_service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.HashMap;

@SpringBootApplication()
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
