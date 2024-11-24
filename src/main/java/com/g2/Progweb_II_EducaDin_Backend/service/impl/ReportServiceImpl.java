package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ReportGeneralDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportServiceImpl implements ReportService{

    @Override
    public ReportGeneralDTO getGeneralBalanceData(Long idUser) {
        return null;
    }

    @Override
    public ReportGeneralDTO getGeneralBalanceDataByPeriod(Long idUser, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public IncomeListDTO getMostRentableIncomesDataPerPeriod(Long idUser, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public IncomeListDTO getMostRentableIncomesData(Long idUser) {
        return null;
    }

    @Override
    public ExpenseListDTO getMostExpensiveExpensesDataPerPeriod(Long idUser, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public ExpenseListDTO getMostExpensiveExpensesData(Long idUser) {
        return null;
    }

    @Override
    public GoalListDTO getMostRentableGoalsDataPerPeriod(Long idUser, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public GoalListDTO getMostRentableGoalsData(Long idUser) {
        return null;
    }

    @Override
    public GoalListDTO getIncompleteGoalsData(Long idUser) {
        return null;
    }

    @Override
    public GoalListDTO getCompleteGoalsData(Long idUser) {
        return null;
    }
}
