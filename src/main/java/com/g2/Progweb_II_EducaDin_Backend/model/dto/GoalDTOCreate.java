package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;

public record GoalDTOCreate(String name,
                            Double amountTotal,
                            LocalDate goalDate) { }
