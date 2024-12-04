package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTOCreateUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface NotificationPreferenceMapper extends GenericMapper<
        NotificationPreferenceDTO,
        NotificationPreferenceDTOCreateUpdate,
        NotificationPreferenceDTOCreateUpdate,
        NotificationPreferenceDTO,
        NotificationPreference,
        Long> {

    @Override
    @Mapping(target = "user.id", source = "userId")
    NotificationPreference fromModelCreatedToModel(NotificationPreferenceDTOCreateUpdate dto);

    @Override
    @Mapping(target = "user.id", source = "userId")
    NotificationPreference fromModelUpdatedToModel(NotificationPreferenceDTOCreateUpdate dto);

    @Override
    @Mapping(target = "userId", source = "user.id")
    NotificationPreferenceDTO toDTO(NotificationPreference model);

    @Override
    @Mapping(target = "userId", source = "user.id")
    @Named(value = "toDTOList")
    NotificationPreferenceDTO toDTOList(NotificationPreference model);
}