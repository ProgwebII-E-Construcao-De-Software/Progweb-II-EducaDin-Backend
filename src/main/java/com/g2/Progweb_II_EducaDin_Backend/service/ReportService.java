package com.g2.Progweb_II_EducaDin_Backend.service;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ReportGeneralDTO;

import java.time.LocalDate;

public interface ReportService{

    ReportGeneralDTO getGeneralBalanceData(Long idUser);

    ReportGeneralDTO getGeneralBalanceDataByPeriod(Long idUser, LocalDate start, LocalDate end);

    IncomeListDTO getMostRentableIncomesDataPerPeriod(Long idUser, LocalDate start, LocalDate end);

    IncomeListDTO getMostRentableIncomesData(Long idUser);

    ExpenseListDTO getMostExpensiveExpensesDataPerPeriod(Long idUser, LocalDate start, LocalDate end);

    ExpenseListDTO getMostExpensiveExpensesData(Long idUser);

    GoalListDTO getMostRentableGoalsDataPerPeriod(Long idUser, LocalDate start, LocalDate end);

    GoalListDTO getMostRentableGoalsData(Long idUser);

    GoalListDTO getIncompleteGoalsData(Long idUser);

    GoalListDTO getCompleteGoalsData(Long idUser);
}
