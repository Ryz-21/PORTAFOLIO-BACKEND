package com.example.backend.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${admin.email}")
    private String adminEmail;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendEmailToAdmin(String clientName, String clientEmail, String clientMessage) {
        String subject = "Nuevo mensaje de contacto de: " + clientName;
        String text = "Has recibido un nuevo mensaje de contacto.\n\n" +
                      "Nombre: " + clientName + "\n" +
                      "Email: " + clientEmail + "\n" +
                      "Mensaje:\n" + clientMessage + "\n\n" +
                      "Responde directamente a: " + clientEmail;
        sendEmail(adminEmail, subject, text);
    }

    public void sendConfirmationEmail(String clientEmail, String clientName) {
        String subject = "Confirmación de mensaje recibido";
        String text = "Hola " + clientName + ",\n\n" +
                      "Gracias por contactarnos. Hemos recibido tu mensaje y nos pondremos en contacto contigo pronto.\n\n" +
                      "Saludos,\nEquipo de Portafolio";
        sendEmail(clientEmail, subject, text);
    }
}