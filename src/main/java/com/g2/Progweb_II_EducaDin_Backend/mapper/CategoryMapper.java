package com.g2.Progweb_II_EducaDin_Backend.mapper;

import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CategoryMapper extends GenericMapper<
        CategoryDTO,
        CategoryDTO,
        CategoryDTO,
        CategoryDTO,
        Category,
        Long> {

    @Mapping(target = "user.id", source = "userId")
    Category fromDTOtoModel(CategoryDTO dto);

    @Override
    @Mapping(target = "user.id", source = "userId")
    Category fromModelCreatedToModel(CategoryDTO dto);

    @Override
    @Mapping(target = "userId", source = "user.id")
    CategoryDTO toDTO(Category model);

    @Override
    @Named(value = "toDTOList")
    @Mapping(target = "userId", source = "user.id")
    CategoryDTO toDTOList(Category model);

    @Override
    @Mapping(target = "user.id", source = "userId")
    Category toModel(CategoryDTO dtoUpdate);

}