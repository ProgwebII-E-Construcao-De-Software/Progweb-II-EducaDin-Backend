package com.g2.Progweb_II_EducaDin_Backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Tip;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/1.0/notifications")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping("/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotifications(@RequestParam Long userId) {
        List<NotificationDTO> notifications = notificationService.getUnreadNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markNotificationAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send-tip")
    public ResponseEntity<NotificationDTO> sendTipAsNotification(
            @RequestParam Long userId,
            @RequestParam String type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/default_tips.json");
            List<Tip> tips = mapper.readValue(inputStream, new TypeReference<List<Tip>>() {});

            List<Tip> filteredTips = tips.stream()
                    .filter(tip -> tip.getType().equalsIgnoreCase(type))
                    .toList();

            if (filteredTips.isEmpty()) {
                throw new RuntimeException("No tips available for type: " + type);
            }

            Tip randomTip = filteredTips.get((int) (Math.random() * filteredTips.size()));

            NotificationDTO notification = new NotificationDTO(
                    null,
                    userId,
                    type,
                    randomTip.getMessage(),
                    false,
                    null
            );

            return ResponseEntity.ok(notification);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load tips from JSON file", e);
        }
    }
}