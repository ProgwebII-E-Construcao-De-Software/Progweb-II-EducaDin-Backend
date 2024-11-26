package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends GenericMapper<
        NotificationDTO,
        NotificationDTO,
        NotificationDTO,
        NotificationDTO,
        Notification,
        Long> {

    @Override
    Notification fromDTOtoModel(NotificationDTO dto);

    @Override
    NotificationDTO fromModeltoDTO(Notification model);
}