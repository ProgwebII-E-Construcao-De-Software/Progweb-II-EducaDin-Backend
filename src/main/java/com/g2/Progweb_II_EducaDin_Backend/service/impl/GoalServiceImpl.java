package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.ErrorValidation;
import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.repository.GoalRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.GoalService;
import com.g2.Progweb_II_EducaDin_Backend.service.IncomeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GoalServiceImpl extends GenericCrudService<Goal, Long, GoalRepository> implements GoalService {


    @Override
    protected void validateBusinessToList(List<Goal> goals) {

    }

    @Override
    protected void prepareToCreate(Goal newModel) {
        newModel.setName(Strings.toRootUpperCase(newModel.getName()));
        newModel.setAmountReached(0.0);
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
            throw new BusinessException("Entitys are too similar : \nmodelPosted : " + newModel + " \nsimilarModel: " + similarModel, ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
    }

    @Override
    protected void prepareToUpdate(Goal newModel, Goal model) {
        newModel.setName(Strings.toRootUpperCase(newModel.getName()));
    }

    @Override
    protected void validateBusinessLogicToUpdate(Goal model) {
        validateBusinessLogic(model);
        if (model.getAmountReached() <= 1.0 || model.getAmountReached() > model.getAmountTotal()) {
            throw new BusinessException("Amount is invalid!: Must be higher than 1.0 and lower than total amount", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
        validateAmbiguous(model);
    }

    @Override
    protected void validateBusinessLogicToDelete(Goal model) {

    }

    @Override
    protected void validateBusinessLogic(Goal model) {
        if (Objects.isNull(model)) {
            throw new BusinessException("Model is null: ", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
        }
        if (model.getAmountTotal() <= 1.0 || model.getAmountTotal() >= 2147483647) {
            throw new BusinessException("Amount Total is invalid!: Must be higher than 1.0 and lower than 2140000000 ", ErrorValidation.BUSINESS_LOGIC_VIOLATION);
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
            return delete(id);
        }
        return null;
    }

}
