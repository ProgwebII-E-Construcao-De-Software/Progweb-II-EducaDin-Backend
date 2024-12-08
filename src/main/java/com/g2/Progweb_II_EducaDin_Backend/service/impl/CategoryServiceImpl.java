package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.repository.CategoryRepository;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends GenericCrudService<Category, Long, CategoryRepository> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean existsByNameAndUserId(String name, Long id) {
        return categoryRepository.existsByNameAndUserId(name, id);
    }

    @Override
    public List<CategoryDTO> getCategoriesByIExpenseAndUserId(boolean IExpense, Long id) {
        List<Category> categories = categoryRepository.findByIExpenseAndUserId(IExpense, id);
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryByNameAndUserId(String name, Long id) {
        return repository.findByNameEqualsIgnoreCaseAndUserId(name, id);
    }


    @Override
    protected void prepareToCreate(Category category) {
        if (category.getUser() != null) {
            User user = userRepository.findById(category.getUser().getId()).orElse(null);
            if (user != null) {
                category.setUser(user);
            }
        }
    }

    @Override
    protected void validateBusinessLogicToCreate(Category newModel) {
        if (newModel.getName() == null || newModel.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
    }

    @Override
    protected void prepareToUpdate(Category newModel, Category model) {
        newModel = model;
    }
    @Override
    public Category update(Category dataToUpdate){
        dataToUpdate = repository.findById(dataToUpdate.getId()).orElse(null);
        return dataToUpdate;

    }
    @Override
    protected void validateBusinessLogicToUpdate(Category model) {
        if (model.getName() == null || model.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
    }

    @Override
    protected void validateBusinessLogic(Category data) {
        if (data.getName() == null || data.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
    }

    @Override
    public List<Category> listAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Category deleteById(Long id) {
        Category category = repository.findById(id).orElse(null);
        if (category != null) {
            repository.delete(category);
        }
        return category;
    }
}
