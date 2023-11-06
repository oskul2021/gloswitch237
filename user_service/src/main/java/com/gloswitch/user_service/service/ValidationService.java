package com.gloswitch.user_service.service;

import com.gloswitch.user_service.models.User;
import com.gloswitch.user_service.models.Validation;
import com.gloswitch.user_service.repositories.UserRepository;
import com.gloswitch.user_service.repositories.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Service
public class ValidationService {
    @Autowired
    private ValidationRepository validationRepository;
    @Autowired
    private UserRepository userRepository;

    public Validation save(Validation validation){
        Random random = new Random();
        int value = random.nextInt(999999);
        String code = String.format("%06d",value);
        Instant created = Instant.now();
        validation.setCreated_at(created);
        validation.setExpire_at(created.plus(5, ChronoUnit.MINUTES));
        validation.setCode(code);
        Validation saved = validationRepository.save(validation);
        return saved;
    }

    public HashMap<String,Object> validate(String code){
        Validation validation = validationRepository.searchCode(code).orElseThrow(() -> new RuntimeException("code unreahable or user account activated"));
        System.out.println(code);
        System.out.println(validation);
        if (Instant.now().isAfter(validation.getExpire_at())){
            throw new RuntimeException("your code has expired");
        }
        User user = validation.getUser();
        user.setAccountstatus(true);
        userRepository.save(user);
        return (HashMap<String, Object>) (new HashMap<String,Object>()).put("msg","the user account has been validated");
    }
}
