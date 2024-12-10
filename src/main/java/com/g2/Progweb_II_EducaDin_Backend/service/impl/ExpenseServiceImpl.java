package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.reflection.ModelReflection;
import br.ueg.progweb2.arquitetura.service.AuthClaimResolve;
import com.g2.Progweb_II_EducaDin_Backend.enums.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import com.g2.Progweb_II_EducaDin_Backend.model.*;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        treatStrings(newModel);
        createFutureExpenses(newModel);
    }

    private static void treatStrings(Expense newModel) {
        newModel.setDescription(newModel.getDescription().trim());
        newModel.setName(newModel.getName().trim().toUpperCase());
    }

    private void getCategory(Expense newModel) {
        Category category = categoryService.getCategoryByNameAndUserId(newModel.getCategory().getName().trim(), newModel.getUser().getId());
        if(Objects.isNull(category)){
            newModel.getCategory().setName(newModel.getCategory().getName().trim().toUpperCase());
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
            throw new InvalidParameterException("expense",  "A repetição deve ser maior que -1: ");
        }
    }

    /**
     *
     * @param newModel
     * Method validates if a given instance its too
     * similar im crucial attributes that make
     * them both invalids by the business logic
     *
     */
    private void validateAmbiguous(Expense newModel) {
        List<Expense> similarModels = repository.findAllByNameIgnoreCaseAndUserId(newModel.getName(), newModel.getUser().getId());

        for(Expense similarModel : similarModels){

            if(ModelReflection.isFieldsIdentical(newModel, similarModel, new String[]{"amount", "leadTime", "description", "name"})
                    && !Objects.equals(similarModel.getId(), newModel.getId())){
                throw new InvalidParameterException("expense",
                        "Entidades muito similares encontradas : \nmodelPosted : "+ newModel + " \nsimilarModel: " + similarModel);
            }
        }
    }

    @Override
    protected void prepareToUpdate(Expense newModel, Expense model) {
        getUser(newModel);
        getCategory(newModel);
        treatStrings(newModel);
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
            throw new InvalidParameterException("expense",  "Entidade nula");
        }
        if(model.getAmount() <= 0.0 || model.getAmount() >= 14000000 )
        {
            throw new InvalidParameterException("expense", "Valor inválido!: Deve ser maior que 0.0 e menor que 14000000");
        }
        if(Objects.isNull(model.getUser()))
        {
            throw new InvalidParameterException("expense", "Usuário não encontrado!");
        }
    }

    @Override
    public List<Expense> listAll() {
        return null;
    }

    @Override
    public List<Expense> listAll(Long userId) {
        return repository.findAllByUserIdAndExpenseDateBefore(userId, LocalDate.now().plusDays(1));
    }

    @Override
    public Page<Expense> listAllByIdPage(Long id, Pageable page) {
        return repository.findByUserIdAndExpenseDateBefore(id, LocalDate.now().plusDays(1), page);
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
            default -> throw new IllegalArgumentException("Tipo de repetição não suportada: " + repeatable);
        };
    }

    @Override
    public Expense deleteById(Long id) {
        Expense model = validateId(id);
        repository.deleteById(id);
        return model;
    }

}
