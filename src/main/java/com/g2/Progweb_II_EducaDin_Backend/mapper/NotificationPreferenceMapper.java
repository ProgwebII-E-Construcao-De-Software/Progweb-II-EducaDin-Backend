package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NotificationPreferenceMapper extends GenericMapper<
        NotificationPreferenceDTO,
        NotificationPreferenceDTO,
        NotificationPreferenceDTO,
        NotificationPreferenceDTO,
        NotificationPreference,
        Long
        > {

    @Override
    NotificationPreference fromDTOtoModel(NotificationPreferenceDTO dto);

    @Override
    NotificationPreference fromDTOCreateToModel(NotificationPreferenceDTO dto);

    @Override
    NotificationPreferenceDTO fromModeltoDTO(NotificationPreference model);

    @Override
    @Named("toDTOList")
    NotificationPreferenceDTO toDTOList(NotificationPreference model);

    @Override
    NotificationPreference fromDTOUpdateToModel(NotificationPreferenceDTO dto);
}
