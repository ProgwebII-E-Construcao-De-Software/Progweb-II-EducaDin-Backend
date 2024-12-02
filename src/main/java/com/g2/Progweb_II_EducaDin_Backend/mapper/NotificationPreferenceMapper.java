package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTOCreateUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NotificationPreferenceMapper extends GenericMapper<
        NotificationPreferenceDTO,
        NotificationPreferenceDTOCreateUpdate,
        NotificationPreferenceDTOCreateUpdate,
        NotificationPreferenceDTO,
        NotificationPreference,
        Long> {

    @Override
    NotificationPreference fromDTOCreateToModel(NotificationPreferenceDTOCreateUpdate dto);

    @Override
    NotificationPreference fromDTOUpdateToModel(NotificationPreferenceDTOCreateUpdate dto);

    @Override
    NotificationPreferenceDTO fromModeltoDTO(NotificationPreference model);

    @Override
    @Named(value = "toDTOList")
    NotificationPreferenceDTO toDTOList(NotificationPreference model);
}