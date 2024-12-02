package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.util.Date;

public record NotificationDTO(
        Long id,
        Long userId,
        String type,
        String message,
        Date createdAt
) {}