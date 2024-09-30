package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;

public record IncomeDTO(Long id,
                        String categoryName,
                        String name,
                        String description,
                        Double amount,
                        Integer leadTime,
                        Boolean repeat,
                        LocalDate incomeDate) {
}
