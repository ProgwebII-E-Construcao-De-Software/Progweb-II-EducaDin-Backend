package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;

public record ExpenseDTOCreateUpdate(String categoryName,
                                     String name,
                                     String description,
                                     Double amount,
                                     LocalDate expenseDate,
                                     Integer leadTime) { }
