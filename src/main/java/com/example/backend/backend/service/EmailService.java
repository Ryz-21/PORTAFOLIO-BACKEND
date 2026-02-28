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
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(adminEmail); 
    message.setSubject("Nuevo mensaje de: " + clientName);
    
    String cuerpoAviso = "Has recibido un nuevo mensaje de contacto.\n\n" +
                         "Nombre: " + clientName + "\n" +
                         "Email: " + clientEmail + "\n" +
                         "Mensaje:\n" + clientMessage + "\n\n" +
                         "-------------------------------------------\n" +
                         "Puedes responder directamente a este correo.";

    message.setText(cuerpoAviso);
    message.setReplyTo(clientEmail); 
    mailSender.send(message);
}

    public void sendConfirmationEmail(String clientEmail, String clientName) {
        String subject = "Confirmación de mensaje recibido";
        String text = "Hola " + clientName + ",\n\n" +
                      "Gracias por contactarme. He recibido tu mensaje y me pondre en contacto contigo pronto.\n\n" +
                      "Saludos,\nLeo Valdivia Suasnabar";
        sendEmail(clientEmail, subject, text);
    }
}