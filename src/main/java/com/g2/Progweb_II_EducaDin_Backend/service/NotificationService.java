package com.g2.Progweb_II_EducaDin_Backend.service;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    NotificationDTO createNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> getUnreadNotifications(Long userId);

    void markNotificationAsRead(Long id);
}