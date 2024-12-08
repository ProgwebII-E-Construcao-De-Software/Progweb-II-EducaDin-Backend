package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;

import java.util.List;

public interface NotificationPreferenceService extends CrudService<NotificationPreference, Long> {
    boolean isNotificationTypeEnabled(Long userId, String type);

    List<NotificationPreference> listAll(Long userId);

    void updateNotificationPreference(Long userId, String type, boolean isEnabled);
}