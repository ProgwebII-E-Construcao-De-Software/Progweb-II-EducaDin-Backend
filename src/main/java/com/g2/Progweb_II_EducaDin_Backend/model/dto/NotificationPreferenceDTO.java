package com.g2.Progweb_II_EducaDin_Backend.model.dto;

public record NotificationPreferenceDTO(
        Long id,
        Long userId,
        String type,
        boolean enabled
) {}