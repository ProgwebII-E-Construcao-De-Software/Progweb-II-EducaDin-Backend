package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.ErrorValidation;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.repository.IncomeRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IncomeServiceImpl extends GenericCrudService<Income, Long, IncomeRepository> implements IncomeService {
   @Autowired
   CategoryService categoryService;

    @Override
    protected void validateBusinessToList(List<Income> incomes) {

    }

    @Override
    protected void prepareToCreate(Income newModel) {
        categoryService.create(newModel.getCategory());
    }

    @Override
    protected void validateBusinessLogicToCreate(Income newModel) {
        validateBusinessLogic(newModel);
    }

    @Override
    protected void prepareToUpdate(Income newModel, Income model) {

    }

    @Override
    protected void validateBusinessLogicToUpdate(Income model) {
        validateBusinessLogic(model);
    }

    @Override
    protected void validateBusinessLogicToDelete(Income model) {

    }

    @Override
    protected void validateBusinessLogic(Income model) {
        if(Objects.isNull(model)){
            throw new BusinessException(ErrorValidation.GENERAL);
        }
        if(model.getAmount() <= 0.0 || model.getAmount() >= 14000000 )
        {
            throw new BusinessException(ErrorValidation.GENERAL);
        }
    }

    @Override
    public List<Income> listAll() {
        return List.of();
    }

    @Override
    public Income deleteById(Long id) {
        return null;
    }
}
