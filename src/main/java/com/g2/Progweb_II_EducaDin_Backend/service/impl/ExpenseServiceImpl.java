package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import br.ueg.progweb2.arquitetura.util.ModelReflection;
import br.ueg.progweb2.exampleuse.exceptions.ErrorValidation;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.repository.ExpenseRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExpenseServiceImpl extends GenericCrudService<Expense, Long, ExpenseRepository> implements ExpenseService {
    @Autowired
    CategoryService categoryService;

    @Override
    protected void validateBusinessToList(List<Expense> dataList) {

    }

    @Override
    protected void prepareToCreate(Expense newModel) {
        String name = newModel.getCategory().getName();
        if(categoryService.existsByName(name)){
            newModel.setCategory(categoryService.create(categoryService.getCategoryByName(name)));
        }
        else{
            newModel.setCategory(categoryService.create(newModel.getCategory()));
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

    @Override
    protected void validateBusinessToList(Expense data) {

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
    }


    @Override
    protected void validateBusinessLogicToUpdate(Expense model) {
        validateBusinessLogic(model);
        validateAmbiguous(model);
    }

    @Override
    protected void validateBusinessLogicToDelete(Expense model) {

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
        return repository.findAll();
    }

}
