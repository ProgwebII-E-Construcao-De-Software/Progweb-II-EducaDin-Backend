package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import com.g2.Progweb_II_EducaDin_Backend.model.*;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IncomeServiceImpl extends GenericCrudService<Income, Long, IncomeRepository> implements IncomeService {
   @Autowired
   CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void prepareToCreate(Income newModel) {
        getUser(newModel);
        getCategory(newModel);
        if (Objects.isNull(newModel.getRepeatable()) || Objects.isNull(newModel.getRepeatable().getName())) {
            newModel.setRepeatable(Repeatable.DONT_REPEATS);
            newModel.setLeadTime(0);
        }
        threatStrings(newModel);
        createFutureIncomes(newModel);
    }

    private static void threatStrings(Income newModel) {
        newModel.setDescription(newModel.getDescription().trim());
        newModel.setName(newModel.getName().trim());
    }

    private void getCategory(Income newModel) {
        Category category = categoryService.getCategoryByNameAndUserId(newModel.getCategory().getName().trim(), newModel.getUser().getId());
        if(Objects.isNull(category)){
            newModel.getCategory().setName(newModel.getCategory().getName().trim());
            category = categoryService.create(newModel.getCategory());
            category.setUser(newModel.getUser());
            category.setIExpense(false);
            newModel.setCategory(category);
        }

    }

    private void getUser(Income newModel) {
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if(Objects.isNull(user)){
            throw new InvalidParameterException("user", "Usuário não encontrado.");
        }
        newModel.setUser(user);
    }


    @Override
    protected void validateBusinessLogicToCreate(Income newModel) {
        validateBusinessLogic(newModel);
        validateAmbiguous(newModel);
        if(newModel.getLeadTime() < 0){
            throw new InvalidParameterException("income",  "LeadTime must be higher than -1: ");
        }
    }

    /**
     *
     * @param newModel
     * Method validates if a given instance its too
     * similar im crucial attributes that make
     * them both invalids by the business logic
     *
     * @throws BusinessException ErrorValidation.GENERAL
     */
    private void validateAmbiguous(Income newModel) {
        List<Income> similarModels = repository.findAllByName(newModel.getName());

        for(Income similarModel : similarModels){

            if(ModelReflection.isFieldsIdentical(newModel, similarModel, new String[]{"amount", "leadTime", "description"})
                && !Objects.equals(similarModel.getId(), newModel.getId())){
                throw new InvalidParameterException("income", 
                       "Entitys are too similar : \nmodelPosted : "+ newModel + " \nsimilarModel: " + similarModel);
            }
        }
    }

    @Override
    protected void prepareToUpdate(Income newModel, Income model) {
        getUser(newModel);
        getCategory(newModel);
        threatStrings(newModel);
        if (Objects.isNull(newModel.getRepeatable()) || Objects.isNull(newModel.getRepeatable().getName())) {
            newModel.setRepeatable(Repeatable.DONT_REPEATS);
            newModel.setLeadTime(0);
        }
        if (newModel.getRepeatable() != Repeatable.DONT_REPEATS || !newModel.getIncomeDate().equals(model.getIncomeDate())) {
            repository.deleteByNameAndUserAndCategoryAndIncomeDateAfter(
                    model.getName(),
                    model.getUser(),
                    model.getCategory(),
                    model.getIncomeDate()
            );
            createFutureIncomes(newModel);
        }
    }


    @Override
    protected void validateBusinessLogicToUpdate(Income model) {
        validateBusinessLogic(model);
        validateAmbiguous(model);
    }


    @Override
    protected void validateBusinessLogic(Income model) {
        if(Objects.isNull(model)){
            throw new InvalidParameterException("income",  "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
        if(model.getAmount() <= 0.0 || model.getAmount() >= 14000000 )
        {
            throw new InvalidParameterException("income", "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
        if(Objects.isNull(model.getUser()))
        {
            throw new InvalidParameterException("income", "Missing user");
        }

    }

    @Override
    public List<Income> listAll() {
        return repository.findAllByIncomeDateBeforeOrIncomeDateEquals(LocalDate.now(), LocalDate.now());
    }

    @Override
    public List<Income> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<Income> listAllContemporaneous(Long userId) {
        return repository.findAllByUserIdAndIncomeDateBefore(userId, LocalDate.now().plusDays(1));
    }

    protected Income validateId(Long id) {
        Optional<Income> incomeOptional = repository.findById(id);
        return incomeOptional.orElse(null);
    }

    public void createFutureIncomes(Income baseIncome) {
        if (baseIncome.getRepeatable() != Repeatable.DONT_REPEATS && baseIncome.getLeadTime() > 0) {
            LocalDate nextDate = baseIncome.getIncomeDate();
            for (int i = 0; i < baseIncome.getLeadTime(); i++) {
                nextDate = calculateNextDate(nextDate, baseIncome.getRepeatable());

                Income futureIncome = Income.builder()
                        .name(baseIncome.getName())
                        .description(baseIncome.getDescription())
                        .amount(baseIncome.getAmount())
                        .incomeDate(nextDate)
                        .leadTime(0)
                        .repeatable(Repeatable.DONT_REPEATS)
                        .category(baseIncome.getCategory())
                        .user(baseIncome.getUser())
                        .build();
                repository.save(futureIncome);
            }
        }
    }


    private LocalDate calculateNextDate(LocalDate currentDate, Repeatable repeatable) {
        return switch (repeatable) {
            case WEEKLY -> currentDate.plusWeeks(1);
            case MONTHLY -> currentDate.plusMonths(1);
            case YEARLY -> currentDate.plusYears(1);
            default -> throw new IllegalArgumentException("Unsupported Repeatable type: " + repeatable);
        };
    }

    @Override
    public Income deleteById(Long id) {
        Income model = validateId(id);
            repository.deleteById(id);
            return model;
    }

}
