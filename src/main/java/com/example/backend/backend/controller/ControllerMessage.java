package com.example.backend.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.backend.service.EmailService;
import com.example.backend.backend.model.Message;
import com.example.backend.backend.repository.MessageRepository;

@RestController
@RequestMapping("/api")
public class ControllerMessage {

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody Message message) {
        // Save message to database
        messageRepository.save(message);

        // Send email to admin with client message details
        emailService.sendEmailToAdmin(message.getName(), message.getEmail(), message.getContent());

        // Send confirmation email to client
        emailService.sendConfirmationEmail(message.getEmail(), message.getName());

        return "Email sent successfully";
    }
}
