package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByName(String name);
}