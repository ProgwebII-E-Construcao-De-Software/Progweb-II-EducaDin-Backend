package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.repository.NotificationPreferenceRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotificationPreferenceServiceImpl extends GenericCrudService<NotificationPreference, Long, NotificationPreferenceRepository>
        implements NotificationPreferenceService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void prepareToCreate(NotificationPreference newModel) {
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (user != null) {
            newModel.setUser(user);
        }
    }

    @Override
    protected void validateBusinessLogicToCreate(NotificationPreference newModel) {
        validateBusinessLogic(newModel);
        if (repository.existsByUserIdAndType(newModel.getUser().getId(), newModel.getType())) {
            throw new InvalidParameterException("notification", "A preference for this type already exists for the user.");
        }
    }

    @Override
    protected void prepareToUpdate(NotificationPreference newModel, NotificationPreference model) {
        if (newModel.getEnabled() != model.isEnabled()) {
            model.setEnabled(newModel.isEnabled());
        }
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (user != null) {
            newModel.setUser(user);
        }
    }


    @Override
    protected void validateBusinessLogicToUpdate(NotificationPreference model) {
        if (Objects.isNull(model)) {
            throw new InvalidParameterException("notification",  "Model is null.");
        }
        if (Objects.isNull(model.getEnabled())) {
            throw new InvalidParameterException("enabled",  "Enabled field cannot be null.");
        }
    }

    @Override
    protected void validateBusinessLogic(NotificationPreference model) {
        if (Objects.isNull(model)) {
            throw new InvalidParameterException("notification",
                    "Model is null." );
        }
        if (Objects.isNull(model.getUser().getId())) {
            throw new InvalidParameterException("id",
                    "User ID cannot be null.");
        }
        if (Objects.isNull(model.getType()) || model.getType().isEmpty()) {
            throw new InvalidParameterException("type",
                    "Notification type cannot be null or empty.");
        }
    }

    @Override
    public List<NotificationPreference> listAll() {
        return repository.findAll();
    }

    @Override
    public List<NotificationPreference> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public boolean isNotificationTypeEnabled(Long userId, String type) {
        return repository.existsByUserIdAndType(userId, type);
    }

}