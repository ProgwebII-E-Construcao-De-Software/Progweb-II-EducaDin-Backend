package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.TipMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Tip;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.TipDTO;
import com.g2.Progweb_II_EducaDin_Backend.repository.TipRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import com.g2.Progweb_II_EducaDin_Backend.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TipServiceImpl extends GenericCrudService<Tip, Long, TipRepository> implements TipService {

    @Autowired
    private NotificationPreferenceService preferenceService;

    @Autowired
    private TipMapper tipMapper;

    @Override
    public TipDTO getRandomTipByType(String type, Long userId) {

        boolean isEnabled = preferenceService.isNotificationTypeEnabled(userId, type);
        if (!isEnabled) {
            throw new BusinessException("Tips of this type are disabled for the user.",
                    ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }


        List<Tip> tips = repository.findByType(type);
        if (tips.isEmpty()) {
            throw new BusinessException("No tips found for the specified type.",
                    ErrorValidation.NOT_FOUND);
        }


        Random random = new Random();
        Tip randomTip = tips.get(random.nextInt(tips.size()));
        return tipMapper.fromModeltoDTO(randomTip);
    }

    @Override
    protected void validateBusinessLogicToCreate(Tip model) {
        if (model.getType() == null || model.getType().isEmpty()) {
            throw new BusinessException("The type of the tip cannot be null or empty.",
                    ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
        if (model.getMessage() == null || model.getMessage().isEmpty()) {
            throw new BusinessException("The message of the tip cannot be null or empty.",
                    ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
    }

    @Override
    protected void validateBusinessLogicToUpdate(Tip model) {
        if (model.getId() == null) {
            throw new BusinessException("The ID of the tip cannot be null.",
                    ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
        validateBusinessLogicToCreate(model);
    }

    @Override
    protected void validateBusinessLogicToDelete(Tip model) {
        if (model == null || model.getId() == null) {
            throw new BusinessException("The tip to delete must have a valid ID.",
                    ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
    }

    @Override
    protected void validateBusinessLogic(Tip data) {

    }

    @Override
    protected void validateBusinessToList(List<Tip> tips) {

    }

    @Override
    protected void prepareToCreate(Tip newModel) {

    }

    @Override
    protected void prepareToUpdate(Tip newModel, Tip existingModel) {

        if (!newModel.getType().equals(existingModel.getType())) {
            existingModel.setType(newModel.getType());
        }
        if (!newModel.getMessage().equals(existingModel.getMessage())) {
            existingModel.setMessage(newModel.getMessage());
        }
    }

    @Override
    public List<Tip> listAll() {
        return List.of();
    }

    @Override
    public Tip deleteById(Long id) {
        return null;
    }
}