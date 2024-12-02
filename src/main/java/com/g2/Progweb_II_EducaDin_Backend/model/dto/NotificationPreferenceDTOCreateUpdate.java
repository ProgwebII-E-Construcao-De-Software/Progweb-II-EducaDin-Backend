package com.g2.Progweb_II_EducaDin_Backend.model.dto;

public record NotificationPreferenceDTOCreateUpdate(
        Long userId,
        String type,
        boolean enabled
) {}