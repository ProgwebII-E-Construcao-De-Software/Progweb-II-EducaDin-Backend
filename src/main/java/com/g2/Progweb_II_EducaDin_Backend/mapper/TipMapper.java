package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Tip;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.TipDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TipMapper extends GenericMapper<
        TipDTO,
        TipDTO,
        TipDTO,
        TipDTO,
        Tip,
        Long> {

    @Override
    Tip fromModelCreatedToModel(TipDTO dto);

    @Override
    Tip fromModelUpdatedToModel(TipDTO dto);

    @Override
    TipDTO toDTO(Tip model);

    @Override
    @Named(value = "toDTOList")
    TipDTO toDTOList(Tip model);
}