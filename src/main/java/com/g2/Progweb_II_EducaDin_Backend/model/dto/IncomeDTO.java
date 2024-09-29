package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import com.g2.Progweb_II_EducaDin_Backend.model.Category;

public record IncomeDTO(Long id,
                        Category category,
                        String name,
                        String description,
                        Double price) {
}
