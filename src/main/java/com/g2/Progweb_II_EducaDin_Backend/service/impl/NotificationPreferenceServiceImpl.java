package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import com.g2.Progweb_II_EducaDin_Backend.repository.NotificationPreferenceRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotificationPreferenceServiceImpl extends GenericCrudService<NotificationPreference, Long, NotificationPreferenceRepository>
        implements NotificationPreferenceService {

    @Override
    protected void prepareToCreate(NotificationPreference newModel) {
    }

    @Override
    protected void validateBusinessLogicToCreate(NotificationPreference newModel) {
        validateBusinessLogic(newModel);
        if (repository.existsByUserIdAndType(newModel.getUserId(), newModel.getType())) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "A preference for this type already exists for the user.");
        }
    }

    @Override
    protected void prepareToUpdate(NotificationPreference newModel, NotificationPreference model) {
        if (newModel.getEnabled() != model.isEnabled()) {
            model.setEnabled(newModel.isEnabled());
        }
    }


    @Override
    protected void validateBusinessLogicToUpdate(NotificationPreference model) {
        if (Objects.isNull(model)) {
            throw new BusinessException( ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Model is null.");
        }
        if (Objects.isNull(model.getEnabled())) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Enabled field cannot be null.");
        }
    }

    @Override
    protected void validateBusinessLogic(NotificationPreference model) {
        if (Objects.isNull(model)) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION,
                    "Model is null." );
        }
        if (Objects.isNull(model.getUserId())) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION,
                    "User ID cannot be null.");
        }
        if (Objects.isNull(model.getType()) || model.getType().isEmpty()) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION,
                    "Notification type cannot be null or empty.");
        }
    }

    @Override
    public List<NotificationPreference> listAll() {
        return repository.findAll();
    }

    @Override
    public boolean isNotificationTypeEnabled(Long userId, String type) {
        return repository.existsByUserIdAndType(userId, type);
    }

}