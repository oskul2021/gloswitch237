package com.gloswitch.user_service.service;

import com.gloswitch.user_service.models.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Notification {
    JavaMailSender mailSender;

    public void send(Validation validation){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("developer3.jstechno@gmail.com");
        mailMessage.setTo(validation.getUser().getEmail());
        mailMessage.setSubject("Code Verification");
        String message = String.format("Hello %s <br/>, find your verification code below\n" +
                "<h3>%s</h3> Please do not respond to this email\n" +
                "<br/>Sincerely,\n" +
                "<br/>The GloSwitch Team",validation.getUser().getFirstname(),validation.getCode());
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
