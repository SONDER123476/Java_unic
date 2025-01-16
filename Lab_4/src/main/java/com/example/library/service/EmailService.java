package com.example.library.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = createEmailMessage(to, subject, body);
        mailSender.send(message);
    }

    private SimpleMailMessage createEmailMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("beliaev.vano@yandex.ru"); // Укажите здесь email-адрес отправителя
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        return message;
    }
}
