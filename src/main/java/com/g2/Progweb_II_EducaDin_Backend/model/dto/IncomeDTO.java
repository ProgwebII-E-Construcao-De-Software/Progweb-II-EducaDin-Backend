package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import com.g2.Progweb_II_EducaDin_Backend.model.CategoryModel;

public record IncomeDTO(Long id,
                        CategoryModel category,
                        String name,
                        String description,
                        Double price) {
}
