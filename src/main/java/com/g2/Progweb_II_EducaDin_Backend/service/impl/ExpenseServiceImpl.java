package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import com.g2.Progweb_II_EducaDin_Backend.model.*;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseServiceImpl extends GenericCrudService<Expense, Long, ExpenseRepository> implements ExpenseService {
    @Autowired
    CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void prepareToCreate(Expense newModel) {
        getUser(newModel);
        getCategory(newModel);
        if (Objects.isNull(newModel.getRepeatable()) || Objects.isNull(newModel.getRepeatable().getName())) {
            newModel.setRepeatable(Repeatable.DONT_REPEATS);
            newModel.setLeadTime(0);
        }
        threatStrings(newModel);
        createFutureExpenses(newModel);
    }

    private void getCategory(Expense newModel) {
        Category category = categoryService.getCategoryByNameAndUserId(newModel.getCategory().getName(), newModel.getUser().getId());
        if(Objects.isNull(category)){
            category = categoryService.create(newModel.getCategory());
            category.setUser(newModel.getUser());
            category.setIExpense(false);
            newModel.setCategory(category);
        }

    }

    private void getUser(Expense newModel) {
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if(Objects.isNull(user)){
            throw new InvalidParameterException("user", "Usuário não encontrado.");
        }
        newModel.setUser(user);
    }


    @Override
    protected void validateBusinessLogicToCreate(Expense newModel) {
        validateBusinessLogic(newModel);
        validateAmbiguous(newModel);
        if(newModel.getLeadTime() < 0){
            throw new InvalidParameterException("expense",  "LeadTime must be higher than -1: ");
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
    private void validateAmbiguous(Expense newModel) {
        List<Expense> similarModels = repository.findAllByName(newModel.getName());

        for(Expense similarModel : similarModels){

            if(ModelReflection.isFieldsIdentical(newModel, similarModel, new String[]{"amount", "leadTime", "description"})
                    && !Objects.equals(similarModel.getId(), newModel.getId())){
                throw new InvalidParameterException("expense",
                        "Entitys are too similar : \nmodelPosted : "+ newModel + " \nsimilarModel: " + similarModel);
            }
        }
    }

    @Override
    protected void prepareToUpdate(Expense newModel, Expense model) {
        getUser(newModel);
        getCategory(newModel);
        if (Objects.isNull(newModel.getRepeatable()) || Objects.isNull(newModel.getRepeatable().getName())) {
            newModel.setRepeatable(Repeatable.DONT_REPEATS);
            newModel.setLeadTime(0);
        }
        if (newModel.getRepeatable() != Repeatable.DONT_REPEATS || !newModel.getExpenseDate().equals(model.getExpenseDate())) {
            repository.deleteByNameAndUserAndCategoryAndExpenseDateAfter(
                    model.getName(),
                    model.getUser(),
                    model.getCategory(),
                    model.getExpenseDate()
            );
            threatStrings(newModel);
            createFutureExpenses(newModel);
        }
    }

    private static void threatStrings(Expense newModel) {
        newModel.setDescription(newModel.getDescription().trim());
        newModel.setName(newModel.getName().trim());
    }

    @Override
    protected void validateBusinessLogicToUpdate(Expense model) {
        validateBusinessLogic(model);
        validateAmbiguous(model);
    }


    @Override
    protected void validateBusinessLogic(Expense model) {
        if(Objects.isNull(model)){
            throw new InvalidParameterException("expense",  "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
        if(model.getAmount() <= 0.0 || model.getAmount() >= 14000000 )
        {
            throw new InvalidParameterException("expense", "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
        if(Objects.isNull(model.getUser()))
        {
            throw new InvalidParameterException("expense", "Missing user");
        }

    }

    @Override
    public List<Expense> listAll() {
        return repository.findAllByExpenseDateBeforeOrExpenseDateEquals(LocalDate.now(), LocalDate.now());
    }

    @Override
    public List<Expense> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<Expense> listAllContemporaneous(Long userId) {
        return repository.findAllByUserIdAndExpenseDateBefore(userId, LocalDate.now().plusDays(1));
    }

    protected Expense validateId(Long id) {
        Optional<Expense> expenseOptional = repository.findById(id);
        return expenseOptional.orElse(null);
    }

    public void createFutureExpenses(Expense baseExpense) {
        if (baseExpense.getRepeatable() != Repeatable.DONT_REPEATS && baseExpense.getLeadTime() > 0) {
            LocalDate nextDate = baseExpense.getExpenseDate();
            for (int i = 0; i < baseExpense.getLeadTime(); i++) {
                nextDate = calculateNextDate(nextDate, baseExpense.getRepeatable());

                Expense futureExpense = Expense.builder()
                        .name(baseExpense.getName())
                        .description(baseExpense.getDescription())
                        .amount(baseExpense.getAmount())
                        .expenseDate(nextDate)
                        .leadTime(0)
                        .repeatable(Repeatable.DONT_REPEATS)
                        .category(baseExpense.getCategory())
                        .user(baseExpense.getUser())
                        .build();
                repository.save(futureExpense);
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
    public Expense deleteById(Long id) {
        Expense model = validateId(id);
        repository.deleteById(id);
        return model;
    }

}
