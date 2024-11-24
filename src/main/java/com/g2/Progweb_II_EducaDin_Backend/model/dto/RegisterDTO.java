package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import com.g2.Progweb_II_EducaDin_Backend.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
