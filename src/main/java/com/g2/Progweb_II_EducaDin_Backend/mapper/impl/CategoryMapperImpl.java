package com.g2.Progweb_II_EducaDin_Backend.mapper.impl;

import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category fromDTOtoModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIExpense(categoryDTO.IExpense());
        return category;
    }

    @Override
    public Category fromDTOCreateToModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIExpense(categoryDTO.IExpense());
        return category;
    }

    @Override
    public Category fromDTOUpdateToModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        category.setIExpense(categoryDTO.IExpense());
        return category;
    }

    @Override
    public CategoryDTO fromModeltoDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getIExpense());
    }

    @Named("toDTOList")
    @Override
    public CategoryDTO toDTOList(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getIExpense());
    }

    @IterableMapping(qualifiedByName = "toDTOList")
    @Override
    public List<CategoryDTO> fromModelToDTOList(List<Category> categories) {
        return categories.stream()
                .map(this::toDTOList)
                .collect(Collectors.toList());
    }

    @Override
    public void updateModelFromModel(Category target, Category source) {
        target.setName(source.getName());
        target.setIExpense(source.getIExpense());
    }
}