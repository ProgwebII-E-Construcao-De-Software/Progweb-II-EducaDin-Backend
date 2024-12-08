package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.SettingsDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Settings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface SettingsMapper extends GenericMapper<
        SettingsDTO,
        SettingsDTO,
        SettingsDTO,
        SettingsDTO,
        Settings,
        Long> {

    @Override
    @Mapping(target = "user.id", source = "userId")
    Settings fromModelCreatedToModel(SettingsDTO dto);

    @Override
    @Mapping(target = "user.id", source = "userId")
    Settings fromModelUpdatedToModel(SettingsDTO dto);

    @Override
    @Mapping(target = "userId", source = "user.id")
    SettingsDTO toDTO(Settings model);

    @Override
    @Mapping(target = "userId", source = "user.id")
    @Named(value = "toDTOList")
    SettingsDTO toDTOList(Settings model);
}