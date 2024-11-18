package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;
import com.g2.Progweb_II_EducaDin_Backend.mapper.NotificationMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import com.g2.Progweb_II_EducaDin_Backend.repository.NotificationRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationService;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationPreferenceService preferenceService;

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        boolean isEnabled = preferenceService.isNotificationTypeEnabled(notificationDTO.userId(), notificationDTO.type());
        if (!isEnabled) {
            throw new RuntimeException("Notifications of this type are disabled for the user.");
        }

        Notification notification = notificationMapper.fromDTOtoModel(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.fromModeltoDTO(savedNotification);
    }

    @Override
    public List<NotificationDTO> getUnreadNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdAndReadFalse(userId);
        return notifications.stream().map(notificationMapper::fromModeltoDTO).collect(Collectors.toList());
    }

    @Override
    public void markNotificationAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found."));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}