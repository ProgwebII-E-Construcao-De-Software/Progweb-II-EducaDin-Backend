package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.NotificationMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;
import com.g2.Progweb_II_EducaDin_Backend.repository.NotificationRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationService;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl extends GenericCrudService<Notification, Long, NotificationRepository>
        implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationPreferenceService preferenceService;

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {

        boolean isEnabled = preferenceService.isNotificationTypeEnabled(notificationDTO.userId(), notificationDTO.type());
        if (!isEnabled) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Notifications of this type are disabled for the user.");
        }

        Notification notification = notificationMapper.toModel(notificationDTO);

        Notification savedNotification = repository.save(notification);
        return notificationMapper.toDTO(savedNotification);
    }

    @Override
    protected void validateBusinessLogicToCreate(Notification model) {

    }

    @Override
    protected void validateBusinessLogicToUpdate(Notification model) {

    }


    @Override
    protected void validateBusinessLogic(Notification data) {

    }

    @Override
    protected void prepareToCreate(Notification newModel) {

    }

    @Override
    protected void prepareToUpdate(Notification newModel, Notification existingModel) {

    }

    @Override
    public List<Notification> listAll() {
        return List.of();
    }

    @Override
    public Notification deleteById(Long id) {
        return null;
    }
}