package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;

import java.time.LocalDate;

public record GoalDTO(Long id,
                      String name,
                      Double amountReached,
                      Double amountTotal,
                      LocalDate goalDate) {
}
