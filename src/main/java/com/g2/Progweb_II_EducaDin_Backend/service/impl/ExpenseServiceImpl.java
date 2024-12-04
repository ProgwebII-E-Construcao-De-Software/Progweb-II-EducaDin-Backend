package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
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
        if(Objects.nonNull(newModel.getUser())){
            String name = newModel.getCategory().getName();
            newModel.getCategory().setUser(newModel.getUser());
            if(categoryService.existsByName(name)){
                newModel.setCategory(categoryService.create(categoryService.getCategoryByName(name)));
            }
            else{
                newModel.setCategory(categoryService.create(newModel.getCategory()));
            }
            if(Objects.isNull(newModel.getRepeatable())){
                newModel.setRepeatable(Repeatable.DONT_REPEATS);
                User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
                if (user != null) {
                    newModel.setUser(user);
                    createFutureExpenses(newModel);
                }
            }
        }
    }

    @Override
    protected void validateBusinessLogicToCreate(Expense newModel) {
        validateBusinessLogic(newModel);
        validateAmbiguous(newModel);
        if(newModel.getLeadTime() < 0){
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION);
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
                throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Entitys are too similar : \nmodelPosted : "+ newModel + " \nsimilarModel: " + similarModel);
            }
        }
    }

    @Override
    protected void prepareToUpdate(Expense newModel, Expense model) {
        String name = newModel.getCategory().getName();
        if(categoryService.existsByName(name)){
            newModel.setCategory(categoryService.create(categoryService.getCategoryByName(name)));
        }
        else{
            newModel.setCategory(categoryService.create(newModel.getCategory()));
        }
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (user != null) {
            newModel.setUser(user);
        }

        if (newModel.getRepeatable() != Repeatable.DONT_REPEATS || !newModel.getExpenseDate().equals(model.getExpenseDate())) {
            repository.deleteByNameAndUserAndCategoryAndExpenseDateAfter(
                    model.getName(),
                    model.getUser(),
                    model.getCategory(),
                    model.getExpenseDate()
            );
            createFutureExpenses(newModel);
        }
    }


    @Override
    protected void validateBusinessLogicToUpdate(Expense model) {
        validateBusinessLogic(model);
        validateAmbiguous(model);
    }


    @Override
    protected void validateBusinessLogic(Expense model) {
        if(Objects.isNull(model)){
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Model is null: ");
        }
        if(model.getAmount() <= 0.0 || model.getAmount() >= 14000000 )
        {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
    }

    @Override
    public List<Expense> listAll() {
        return repository.findAllByExpenseDateBeforeOrExpenseDateEquals(LocalDate.now(), LocalDate.now());
    }

    public List<Expense> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<Expense> listAllContemporaneous(Long userId) {
        return repository.findAllByUserIdAndExpenseDateBefore(userId, LocalDate.now().plusDays(1));
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

    protected Expense validateId(Long id) {
        Optional<Expense> exOptional = repository.findById(id);
        return exOptional.orElse(null);
    }

    @Override
    public Expense deleteById(Long id) {
        Expense model = validateId(id);
        if (Objects.nonNull(model)) {
            repository.deleteByNameAndUserAndCategoryAndExpenseDateAfter(
                    model.getName(), model.getUser(), model.getCategory(), model.getExpenseDate()
            );
            repository.deleteById(id);
            return model;
        }
        return null;
    }

}
