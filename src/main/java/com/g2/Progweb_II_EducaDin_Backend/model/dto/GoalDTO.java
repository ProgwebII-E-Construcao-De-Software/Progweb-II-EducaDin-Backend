package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;
import java.util.List;

public record GoalDTO(Long id,
                      String name,
                      Double amountReached,
                      Double amountTotal,
                      LocalDate goalDate,
                      Long userId,
                      List<Long> sharedWith) {
}
