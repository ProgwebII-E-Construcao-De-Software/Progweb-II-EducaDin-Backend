package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class IncomeMapper implements GenericMapper<IncomeDTO, IncomeDTOCreateUpdate, IncomeDTOCreateUpdate, IncomeListDTO, Income, Long> {

    @Autowired
    private CategoryService categoryService;

    @Mapping(source = "categoryName", target = "category", qualifiedByName = "resolveCategoryByName")
    @Mapping(target = "user.id", source = "userId")
    public abstract Income toModel(IncomeDTOCreateUpdate dto);

    @Override
    @Mapping(source = "categoryName", target = "category", qualifiedByName = "resolveCategoryByName")
    @Mapping(target = "user.id", source = "userId")
    public abstract Income fromModelCreatedToModel(IncomeDTOCreateUpdate dto);

    @Override
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(target = "userId", source = "user.id")
    public abstract IncomeDTO toDTO(Income model);

    @Override
    @Named("toDTOList")
    @Mapping(target = "userId", source = "user.id")
    public abstract IncomeListDTO toDTOList(Income model);

    @Override
    @Mapping(source = "categoryName", target = "category", qualifiedByName = "resolveCategoryByName")
    @Mapping(target = "user.id", source = "userId")
    public abstract Income fromModelUpdatedToModel(IncomeDTOCreateUpdate incomeDTOCreateUpdate);

    @Named("resolveCategoryByName")
    protected Category resolveCategoryByName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return null;
        }
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category = categoryService.create(category);
        }
        return category;
    }
}
