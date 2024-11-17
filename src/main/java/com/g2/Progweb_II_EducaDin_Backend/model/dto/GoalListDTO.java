package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;

public record GoalListDTO(Long id,
                          String name,
                          Double amountReached,
                          Double amountTotal,
                          LocalDate goalDate,
                          Double goalPercent) {
}
