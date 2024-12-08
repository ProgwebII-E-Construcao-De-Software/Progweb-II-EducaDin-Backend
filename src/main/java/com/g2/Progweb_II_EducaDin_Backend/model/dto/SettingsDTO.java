package com.g2.Progweb_II_EducaDin_Backend.model.dto;

public record SettingsDTO(
        Long userId,
        String email,
        String notificationType,
        boolean notificationsEnabled
) {}
