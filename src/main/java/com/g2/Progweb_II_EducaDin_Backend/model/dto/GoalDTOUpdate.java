package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;

public record GoalDTOUpdate(String name,
                            Double amountReached,
                            Double amountTotal,
                            LocalDate goalDate) { }
