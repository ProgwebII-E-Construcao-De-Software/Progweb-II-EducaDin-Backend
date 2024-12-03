package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;

import java.time.LocalDate;

public record IncomeListDTO(Long id,
                            Category category,
                            String name,
                            String description,
                            Double amount,
                            Integer leadTime,
                            Repeatable repeatable,
                            LocalDate incomeDate,
                            Long userId) {
}
