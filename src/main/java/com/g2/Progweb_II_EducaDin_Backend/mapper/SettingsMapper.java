package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.SettingsDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTO;
import com.g2.Progweb_II_EducaDin_Backend.repository.NotificationPreferenceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = NotificationPreferenceMapper.class)
public abstract class SettingsMapper implements GenericMapper<
        SettingsDTO,
        Void,
        Void,
        SettingsDTO,
        User,
        Long
        > {

    @Autowired
    private NotificationPreferenceMapper notificationPreferenceMapper;

    @Autowired
    private NotificationPreferenceRepository notificationPreferenceRepository;

    @Override
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "preferences", expression = "java(getPreferences(user.getId()))")
    public abstract SettingsDTO toDTO(User user);

    @Mapping(target = "email", source = "email")
    public abstract User updateUserFromDTO(SettingsDTO settingsDTO, @MappingTarget User user);

    public abstract NotificationPreference updatePreferenceFromDTO(NotificationPreferenceDTO dto, @MappingTarget NotificationPreference preference);

    @Named("getPreferences")
    public List<NotificationPreferenceDTO> getPreferences(Long userId) {
        List<NotificationPreference> preferences = notificationPreferenceRepository.findByUserId(userId);
        return preferences.stream()
                .map(notificationPreferenceMapper::toDTO)
                .toList();
    }

    @Override
    public List<SettingsDTO> fromModelToDTOList(List<User> userList) {
        return userList.stream().map(this::toDTO).toList();
    }

    @Override
    public User fromModelCreatedToModel(Void dto) {
        throw new UnsupportedOperationException("Operação não suportada.");
    }

    @Override
    public User fromModelUpdatedToModel(Void dto) {
        throw new UnsupportedOperationException("Operação não suportada.");
    }
}