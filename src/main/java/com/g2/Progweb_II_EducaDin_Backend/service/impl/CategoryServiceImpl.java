package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.repository.CategoryRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends GenericCrudService<Category, Long, CategoryRepository> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public List<CategoryDTO> getCategoriesByIExpense(boolean IExpense) {
        List<Category> categories = categoryRepository.findByIExpense(IExpense);
        return categories.stream()
                .map(categoryMapper::fromModeltoDTO)
                .collect(Collectors.toList());
    }

    @Override
    protected void validateBusinessToList(List<Category> categories) {

    }

    @Override
    protected void prepareToCreate(Category category) {

    }

    @Override
    protected void validateBusinessLogicToCreate(Category newModel) {

    }

    @Override
    protected void prepareToUpdate(Category newModel, Category model) {

    }

    @Override
    protected void validateBusinessLogicToUpdate(Category model) {

    }

    @Override
    protected void validateBusinessLogicToDelete(Category model) {

    }

    @Override
    protected void validateBusinessLogic(Category data) {

    }

    @Override
    public List<Category> listAll() {
        return List.of();
    }

    @Override
    public Category deleteById(Long id) {
        return null;
    }
}