package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseListDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class ExpenseMapper implements GenericMapper<ExpenseDTO, ExpenseDTOCreateUpdate, ExpenseDTOCreateUpdate, ExpenseListDTO, Expense, Long> {

    @Autowired
    private CategoryService categoryService;

    @Mapping(target = "category", expression = "java(resolveCategoryByName(dto.categoryName(), dto.userId()))")
    @Mapping(target = "user.id", source = "userId")
    public abstract Expense toModel(ExpenseDTOCreateUpdate dto);

    @Override
    @Mapping(target = "category", expression = "java(resolveCategoryByName(dto.categoryName(), dto.userId()))")
    @Mapping(target = "user.id", source = "userId")
    public abstract Expense fromModelCreatedToModel(ExpenseDTOCreateUpdate dto);

    @Override
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(target = "userId", source = "user.id")
    public abstract ExpenseDTO toDTO(Expense model);

    @Override
    @Named("toDTOList")
    @Mapping(target = "userId", source = "user.id")
    public abstract ExpenseListDTO toDTOList(Expense model);

    @Override
    @Mapping(target = "category", expression = "java(resolveCategoryByName(expenseDTOCreateUpdate.categoryName(), expenseDTOCreateUpdate.userId()))")
    @Mapping(target = "user.id", source = "userId")
    public abstract Expense fromModelUpdatedToModel(ExpenseDTOCreateUpdate expenseDTOCreateUpdate);

    @Named("resolveCategoryByName")
    protected Category resolveCategoryByName(String categoryName, Long userId) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return null;
        }
        Category category = categoryService.getCategoryByNameAndUserId(categoryName.trim(), userId);
        if (category == null) {
            category = new Category();
            category.setName(categoryName.trim());
            category.setIExpense(true);
            category.setUser(new User());
            category.getUser().setId(userId);
            category = categoryService.create(category);
        }
        return category;
    }
}
