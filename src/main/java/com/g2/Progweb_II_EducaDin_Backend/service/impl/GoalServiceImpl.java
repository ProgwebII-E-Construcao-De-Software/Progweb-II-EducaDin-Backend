package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import com.g2.Progweb_II_EducaDin_Backend.mapper.UserMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.repository.GoalRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.GoalService;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl extends GenericCrudService<Goal, Long, GoalRepository> implements GoalService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    NotificationService notificationService;



    @Override
    protected void prepareToCreate(Goal newModel) {
        newModel.setName(Strings.toRootUpperCase(newModel.getName()));
        newModel.setAmountReached(0.0);
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (user != null) {
            newModel.setUser(user);
        }
    }

    @Override
    protected void validateBusinessLogicToCreate(Goal newModel) {
        validateBusinessLogic(newModel);
        validateAmbiguous(newModel);
    }



    /**
     * @param newModel Method validates if a given instance its too
     *                 similar on crucial attributes that make
     *                 them both invalids by the business logic
     * @throws BusinessException ErrorValidation.GENERAL
     */
    private void validateAmbiguous(Goal newModel) {
        Optional<Goal> similarModel = Optional.ofNullable(repository.findByNameEqualsIgnoreCase(newModel.getName()));
        if (similarModel.isPresent() && !Objects.equals(newModel.getId(), similarModel.get().getId())) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Entitys are too similar : \nmodelPosted : " + newModel + " \nsimilarModel: " + similarModel);
        }
    }

    @Override
    protected void prepareToUpdate(Goal newModel, Goal existingModel) {
        User owner = existingModel.getUser();
        if (owner == null) {
            throw new BusinessException(ErrorValidation.NOT_FOUND, "Owner not found");
        }

        User userEditing = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (userEditing == null) {
            throw new BusinessException(ErrorValidation.NOT_FOUND, "User performing the update not found.");
        }
        if (userEditing.getId().equals(owner.getId())) {
            newModel.setName(Strings.toRootUpperCase(newModel.getName()));
            existingModel.setAmountTotal(newModel.getAmountTotal());
            existingModel.setGoalDate(newModel.getGoalDate());
        } else if (existingModel.getSharedWith().contains(userEditing.getId())) {
            if (!newModel.getAmountReached().equals(existingModel.getAmountReached())) {
                existingModel.setAmountReached(newModel.getAmountReached());
            } else {
                throw new BusinessException(ErrorValidation.UNAUTHORIZED_OPERATION,
                        "Shared users can only update the amount reached.");
            }
        } else {
            throw new BusinessException(ErrorValidation.UNAUTHORIZED_OPERATION,
                    "User does not have permission to update this goal.");
        }
    }

    @Override
    protected void validateBusinessLogicToUpdate(Goal model) {
        validateBusinessLogicToUpdate(model);
        if (model.getAmountReached() < 0.0 || model.getAmountReached() > model.getAmountTotal()) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Amount is invalid!: Must be higher than 1.0 and lower than total amount");
        }
        validateAmbiguous(model);
    }

    @Override
    protected void validateBusinessLogic(Goal model) {
        if (Objects.isNull(model)) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Model is null: ");
        }
        if (model.getAmountTotal() <= 0.0 || model.getAmountTotal() >= 2147483647) {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Amount Total is invalid!: Must be higher than 1.0 and lower than 2140000000 ");
        }

    }

    @Override
    public List<Goal> listAll() {
        return repository.findAll();
    }

    @Override
    public Goal deleteById(Long id) {
        Goal model = validateId(id);
        if (Objects.nonNull(model)) {
            repository.deleteById(id);
            return model;
        }
        return null;
    }

    @Override
    public List<Goal> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    protected Goal validateId(Long id) {
        Optional<Goal> goalOptional = repository.findById(id);
        return goalOptional.orElse(null);
    }

    @Override
    public List<Goal> getGoalsByOwnerOrSharedWith(Long userId) {
        return goalRepository.findGoalsByOwnerOrSharedWith(userId);
    }

}
