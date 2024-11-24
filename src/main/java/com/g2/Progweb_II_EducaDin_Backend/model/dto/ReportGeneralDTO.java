package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import java.time.LocalDate;
import java.util.List;

public record ReportGeneralDTO(Long idUser,
                               List<IncomeListDTO> incomes,
                               List<GoalListDTO> goals,
                               List<ExpenseListDTO> expenses,
                               Integer totalGoalsCompleted,
                               Integer totalGoalsUncompleted,
                               Integer totalIncomes,
                               Integer totalGoals,
                               Integer totalExpenses,
                               LocalDate startDate,
                               LocalDate endDate
                               ) {
}
