package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface NotificationMapper extends GenericMapper<
        NotificationDTO,
        NotificationDTO,
        NotificationDTO,
        NotificationDTO,
        Notification,
        Long> {

    @Override
    @Mapping(target = "user.id", source = "userId")
    Notification toModel(NotificationDTO dto);

    @Override
    @Mapping(target = "userId", source = "user.id")
    NotificationDTO toDTO(Notification model);
}