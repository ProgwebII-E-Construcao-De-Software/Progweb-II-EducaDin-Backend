package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTOCreateUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface IncomeMapper extends GenericMapper<IncomeDTO, IncomeDTOCreateUpdate, IncomeDTOCreateUpdate, IncomeDTO, Income, Long> {

    @Mapping(source = "categoryName", target = "category.name")
    Income fromDTOtoModel(IncomeDTOCreateUpdate dto);

    @Mapping(source = "categoryName", target = "category.name")
    Income fromDTOCreateToModel(IncomeDTOCreateUpdate dto);

    @Named(value = "toDTOList")
    @Mapping(source = "categoryName", target = "category.name")
    Income toDTOList(IncomeDTO model);
}
