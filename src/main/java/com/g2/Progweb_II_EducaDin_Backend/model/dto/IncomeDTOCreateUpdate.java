package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;

public record IncomeDTOCreateUpdate(String categoryName,
                              String name,
                              String description,
                              Double amount,
                              Boolean repeat,
                              LocalDate incomeDate,
                              Integer leadTime) { }
