package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserMapper.class})
public interface IncomeMapper extends GenericMapper<IncomeDTO, IncomeDTOCreateUpdate, IncomeDTOCreateUpdate, IncomeListDTO, Income, Long> {

    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(target = "user.id", source = "userId")
    Income toModel(IncomeDTOCreateUpdate dto);

    @Override
    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(target = "user.id", source = "userId")
    Income fromModelCreatedToModel(IncomeDTOCreateUpdate dto);

    @Override
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(target = "userId", source = "user.id")
    IncomeDTO toDTO(Income model);

    @Override
    @Named(value = "toDTOList")
    @Mapping(target = "userId", source = "user.id")
    IncomeListDTO toDTOList(Income model);

    @Override
    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(target = "user.id", source = "userId")
    Income fromModelUpdatedToModel(IncomeDTOCreateUpdate incomeDTOCreateUpdate);


}
