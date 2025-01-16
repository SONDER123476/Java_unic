package com.example.library.controller;

import com.example.library.model.NotificationCondition;
import com.example.library.repository.NotificationConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationConditionController {

    @Autowired
    private NotificationConditionRepository notificationConditionRepository;

    @PostMapping
    public ResponseEntity<String> createNotificationCondition(@RequestBody NotificationCondition condition) {
        notificationConditionRepository.save(condition);
        return ResponseEntity.ok("Notification condition saved successfully!");
    }
}
