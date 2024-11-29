package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ExpenseMapper extends GenericMapper<ExpenseDTO, ExpenseDTOCreateUpdate, ExpenseDTOCreateUpdate, ExpenseListDTO, Expense, Long> {

    @Mapping(source = "categoryName", target = "category.name")
    Expense fromDTOtoModel(ExpenseDTOCreateUpdate dto);

    @Override
    @Mapping(source = "categoryName", target = "category.name")
    Expense fromModelCreatedToModel(ExpenseDTOCreateUpdate dto);

    @Override
    @Mapping(source = "category.name", target = "categoryName")
    ExpenseDTO toDTO(Expense model);

    @Override
    @Named(value = "toDTOList")
    ExpenseListDTO toDTOList(Expense model);

    @Mapping(source = "categoryName", target = "category.name")
    Expense toModel(ExpenseDTOCreateUpdate dtoUpdate);


}
