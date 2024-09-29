package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.repository.CategoryRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
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
    protected void validateBusinessToList(List<Category> modelList) {

    }

    @Override
    protected void prepareToCreate(Category category) {

    }

    @Override
    protected void validateBusinessLogicToCreate(Category category) {
        if (existsByName(category.getName())) {
            throw new IllegalArgumentException("A categoria com este nome já existe");
        }
    }

    @Override
    protected void prepareToUpdate(Category newModel, Category model) {

    }

    @Override
    protected void validateBusinessLogicToUpdate(Category category) {
        if (existsByName(category.getName())) {
            throw new IllegalArgumentException("A categoria com este nome já existe");
        }
    }

    @Override
    protected void validateBusinessLogicToDelete(Category category) {

    }

    @Override
    protected void validateBusinessLogic(Category data) {

    }

    @Override
    public List<Category> listAll() {
        return getAll();
    }

    @Override
    public Category create(Category model) {
        return super.create(model);
    }

    @Override
    public Category update(Category model) {
        return super.update(model);
    }

    @Override
    public Category delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Category getById(Long id) {
        return super.getById(id);
    }

    @Override
    public Category deleteById(Long id) {
        return super.delete(id);
    }
}