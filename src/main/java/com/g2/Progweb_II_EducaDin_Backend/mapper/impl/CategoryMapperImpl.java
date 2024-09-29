package com.g2.Progweb_II_EducaDin_Backend.mapper.impl;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getIExpense());
    }

    @Override
    public Category fromModelCreatedToModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIExpense(categoryDTO.IExpense());
        return category;
    }

    @Override
    public Category fromModelUpdatedToModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIExpense(categoryDTO.IExpense());
        return category;
    }

    @Override
    public Category toModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIExpense(categoryDTO.IExpense());
        return category;
    }

    @Override
    public CategoryDTO toDTOList(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getIExpense());
    }

    @Override
    public List<CategoryDTO> fromModelToDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateModelFromModel(Category target, Category source) {
        target.setName(source.getName());
        target.setIExpense(source.getIExpense());
    }
}
