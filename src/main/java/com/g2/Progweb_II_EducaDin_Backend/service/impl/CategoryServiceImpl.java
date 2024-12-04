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
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public List<CategoryDTO> getCategoriesByIExpense(boolean IExpense) {
        List<Category> categories = categoryRepository.findByIExpense(IExpense);
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Category getCategoryByName(String name) {
        return repository.findByNameEqualsIgnoreCase(name);
    }

    @Override
    protected void prepareToCreate(Category category) {
        if(Objects.nonNull(category.getUser())){
            User user = userRepository.findById(category.getUser().getId()).orElse(null);
            if (user != null) {
                category.setUser(user);
            }
        }


    }

    @Override
    protected void validateBusinessLogicToCreate(Category newModel) {

    }


    @Override
    protected void prepareToUpdate(Category newModel, Category model) {
        User user = userRepository.findById(newModel.getUser().getId()).orElse(null);
        if (user != null) {
            newModel.setUser(user);
        }
    }

    @Override
    protected void validateBusinessLogicToUpdate(Category model) {

    }

    @Override
    protected void validateBusinessLogic(Category data) {

    }

    @Override
    public List<Category> listAll() {
        return List.of();
    }

    @Override
    public List<Category> listAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Category deleteById(Long id) {
        return null;
    }
}