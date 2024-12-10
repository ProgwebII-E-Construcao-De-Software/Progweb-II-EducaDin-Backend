package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.repository.GoalRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.GoalService;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GoalServiceImpl extends GenericCrudService<Goal, Long, GoalRepository> implements GoalService {

    @Autowired
    private UserRepository userRepository;

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
        Optional<Goal> similarModel = Optional.ofNullable(repository.findAllByNameIgnoreCaseAndUserId(newModel.getName(), newModel.getUser().getId()));
        if (similarModel.isPresent() && !Objects.equals(newModel.getId(), similarModel.get().getId())) {
            throw new InvalidParameterException("income",
                    "Entidades muito similares encontradas : \nmodelPosted : "+ newModel + " \nsimilarModel: " + similarModel);
        }
    }

    @Override
    protected void prepareToUpdate(Goal newModel, Goal model) {
        newModel.setName(Strings.toRootUpperCase(newModel.getName()));
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (user != null) {
            newModel.setUser(user);
        }
    }

    @Override
    protected void validateBusinessLogicToUpdate(Goal model) {
        validateBusinessLogic(model);
        if (model.getAmountReached() < 0.0 || model.getAmountReached() > model.getAmountTotal()) {
            throw new InvalidParameterException("income", "Valor inválido!: Deve ser maior que ou igual a 0.0 e menor que o valor de da meta ");
        }
        validateAmbiguous(model);
    }

    @Override
    protected void validateBusinessLogic(Goal model) {
        if (Objects.isNull(model)) {
            throw new InvalidParameterException("income", "Modelo é nulo");
        }
        if (model.getAmountTotal() <= 0.0 || model.getAmountTotal() >= 2147483647) {
            throw new InvalidParameterException("income", "Valor inválido!: Deve ser maior que 0.0 e menor que  2140000000");
        }

    }

    @Override
    public List<Goal> listAll() {
        return null;
    }

    @Override
    public Page<Goal> listAllByIdPage(Long id, Pageable page) {
        return repository.findByUserId(id, page);
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



}
