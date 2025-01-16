package com.example.library.listener;

import com.example.library.model.ChangeLogEvent;
import com.example.library.service.EmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ChangeLogEventListener implements ApplicationListener<ChangeLogEvent> {

    private final EmailService emailService;

    public ChangeLogEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(ChangeLogEvent event) {
        String adminEmail = "beliaev.vano@gmail.com";

        String message = "Change detected: " + event.getChangeType() + " on entity: " + event.getEntityName() +
                ". Description: " + event.getDescription();

        emailService.sendEmail(adminEmail, "Change Log Notification", message);
    }
}

