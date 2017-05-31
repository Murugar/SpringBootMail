package com.iqmsoft.boot.mail.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSenderController {

    @Autowired
    JavaMailSender mailSender;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String sendEmail() {
    	
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setSubject("Spring Boot");
        message.setText("Hello from Spring Boot Application");
        message.setTo("test@gmail.com");
        message.setFrom("test@gmail.com");
        try {
            mailSender.send(message);
            return "{\"message\": \"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\": \"Error\"}";
        }
    }
    
    @RequestMapping(path = "/attach", method = RequestMethod.GET)
    public String sendEmailAttach() throws MessagingException {
    	
        MimeMessage message = mailSender.createMimeMessage();
        
        MimeMessageHelper h = new  MimeMessageHelper(message, true);
        
        h.setSubject("Spring Boot");
        h.setText("Hello from Spring Boot Mail Application");
        h.setTo("test@gmail.com");
        h.setFrom("test@gmail.com");
        
        FileSystemResource f = new FileSystemResource("/home/test/test1.png");
        h.addAttachment("test.png", f);
        
        
        
        
        try {
            mailSender.send(message);
            return "{\"message\": \"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\": \"Error\"}";
        }
    }


  
}
