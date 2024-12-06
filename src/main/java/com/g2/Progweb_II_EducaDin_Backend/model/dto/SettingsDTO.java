package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.util.List;

public record SettingsDTO(
        Long userId,
        String email,
        List<NotificationPreferenceDTO> preferences
) {}