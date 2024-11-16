package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface IncomeMapper extends GenericMapper<IncomeDTO, IncomeDTOCreateUpdate, IncomeDTOCreateUpdate, IncomeListDTO, Income, Long> {

    @Mapping(source = "categoryName", target = "category.name")
    Income fromDTOtoModel(IncomeDTOCreateUpdate dto);

    @Override
    @Mapping(source = "categoryName", target = "category.name")
    Income fromDTOCreateToModel(IncomeDTOCreateUpdate dto);

    @Override
    @Mapping(source = "category.name", target = "categoryName")
    IncomeDTO fromModeltoDTO(Income model);

    @Override
    @Named(value = "toDTOList")
    IncomeListDTO toDTOList(Income model);

    @Override
    @Mapping(source = "categoryName", target = "category.name")
    Income fromDTOUpdateToModel(IncomeDTOCreateUpdate incomeDTOCreateUpdate);


}
