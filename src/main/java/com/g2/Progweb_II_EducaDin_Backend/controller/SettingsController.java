package com.g2.Progweb_II_EducaDin_Backend.controller;

import com.g2.Progweb_II_EducaDin_Backend.service.UserService;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationPreferenceService notificationPreferenceService;

    @PutMapping("/{userId}/email")
    public ResponseEntity<Void> updateEmail(
            @PathVariable Long userId,
            @RequestParam("email") String email) {
        userService.updateUserEmail(userId, email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/notifications")
    public ResponseEntity<Void> updateNotificationPreference(
            @PathVariable Long userId,
            @RequestParam("type") String notificationType,
            @RequestParam("enabled") boolean notificationsEnabled) {
        notificationPreferenceService.updateNotificationPreference(userId, notificationType, notificationsEnabled);
        return ResponseEntity.ok().build();
    }
}
