package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;

import java.util.List;

public interface NotificationService extends CrudService<Notification, Long> {

    NotificationDTO createNotification(NotificationDTO notificationDTO);
    List<Notification> listAll(Long userId);
}
