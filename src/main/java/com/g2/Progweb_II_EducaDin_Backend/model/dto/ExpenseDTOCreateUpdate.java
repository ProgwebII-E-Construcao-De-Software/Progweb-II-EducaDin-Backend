package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;

import java.time.LocalDate;

public record ExpenseDTOCreateUpdate(String categoryName,
                                     String name,
                                     String description,
                                     Double amount,
                                     LocalDate expenseDate,
                                     Repeatable repeatable,
                                     LocalDate incomeDate,
                                     Integer leadTime) { }
