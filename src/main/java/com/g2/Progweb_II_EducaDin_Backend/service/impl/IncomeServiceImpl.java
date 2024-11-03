package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import br.ueg.progweb2.arquitetura.util.ModelReflection;
import br.ueg.progweb2.exampleuse.exceptions.ErrorValidation;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IncomeServiceImpl extends GenericCrudService<Income, Long, IncomeRepository> implements IncomeService {
   @Autowired
   CategoryService categoryService;


    @Override
    protected void prepareToCreate(Income newModel) {
        String name = newModel.getCategory().getName();
        if(categoryService.existsByName(name)){
            newModel.setCategory(categoryService.create(categoryService.getCategoryByName(name)));
        }
        else{
            newModel.setCategory(categoryService.create(newModel.getCategory()));
        }

    }

    @Override
    protected void validateBusinessLogicToCreate(Income newModel) {
        validateBusinessLogic(newModel);
        validateAmbiguous(newModel);
        if(newModel.getLeadTime() < 0){
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION,"LeadTime must be higher than -1: ");
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
                throw new BusinessException(
                        ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Entitys are too similar : \nmodelPosted : "+ newModel + " \nsimilarModel: " + similarModel);
            }
        }
    }

    @Override
    protected void prepareToUpdate(Income newModel, Income model) {
        String name = newModel.getCategory().getName();
        if(categoryService.existsByName(name)){
            newModel.setCategory(categoryService.create(categoryService.getCategoryByName(name)));
        }
        else{
            newModel.setCategory(categoryService.create(newModel.getCategory()));
        }
    }

    @Override
    protected void validateBusinessLogicToUpdate(Income model) {
        validateBusinessLogic(model);
        validateAmbiguous(model);
    }

    @Override
    protected void validateBusinessLogicToDelete(Income model) {

    }

    @Override
    protected void validateBusinessLogic(Income model) {
        if(Objects.isNull(model)){
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
        if(model.getAmount() <= 0.0 || model.getAmount() >= 14000000 )
        {
            throw new BusinessException(ErrorValidation.BUSINESS_LOGIC_VIOLATION, "Amount is invalid!: Must be higher than 0.0 and lower than 14000000 ");
        }
    }

    @Override
    public List<Income> listAll() {
        return repository.findAll();
    }

    @Override
    protected void validateBusinessToList(List<Income> dataList) {

    }

    @Override
    public Income deleteById(Long id) {
        Income model = validateId(id);
        if(Objects.nonNull(model)){
            repository.deleteById(id);
            return model;
        }
        return null;
    }

    private Income validateId(Long id) {
        Optional<Income> incomeOptional = repository.findById(id);
        return incomeOptional.orElse(null);
    }

    @Override
    protected void validateBusinessToList(Income data) {

    }

}
