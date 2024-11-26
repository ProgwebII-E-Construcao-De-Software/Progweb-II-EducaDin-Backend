package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
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
    protected void validateBusinessToList(List<NotificationPreference> preferences) {
    }

    @Override
    protected void prepareToCreate(NotificationPreference newModel) {
    }

    @Override
    protected void validateBusinessLogicToCreate(NotificationPreference newModel) {
        validateBusinessLogic(newModel);
        if (repository.existsByUserIdAndType(newModel.getUserId(), newModel.getType())) {
            throw new BusinessException("A preference for this type already exists for the user.",
                    ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
    }

    @Override
    protected void prepareToUpdate(NotificationPreference newModel, NotificationPreference existingModel) {
        newModel.setId(existingModel.getId());
        if (Objects.isNull(newModel.getType()) || newModel.getType().isEmpty()) {
            throw new BusinessException("Notification type cannot be null or empty.", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
    }

    @Override
    protected void validateBusinessLogicToUpdate(NotificationPreference model) {
        validateBusinessLogic(model);
    }

    @Override
    protected void validateBusinessLogicToDelete(NotificationPreference model) {
    }

    @Override
    protected void validateBusinessLogic(NotificationPreference model) {
        if (Objects.isNull(model)) {
            throw new BusinessException("Model is null.", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
        if (Objects.isNull(model.getUserId())) {
            throw new BusinessException("User ID cannot be null.", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
        if (Objects.isNull(model.getType()) || model.getType().isEmpty()) {
            throw new BusinessException("Notification type cannot be null or empty.", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
    }

    @Override
    public List<NotificationPreference> listAll() {
        return repository.findAll();
    }

    @Override
    public NotificationPreference deleteById(Long id) {
        NotificationPreference model = validateId(id);
        if (Objects.nonNull(model)) {
            return delete(id);
        }
        return null;
    }

    @Override
    public boolean isNotificationTypeEnabled(Long userId, String type) {
        return repository.existsByUserIdAndType(userId, type);
    }
}