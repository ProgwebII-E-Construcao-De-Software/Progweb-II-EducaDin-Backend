package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    List<Expense> findAllByName(String name);

    List<Expense> findAllByUserId(long userId);

    @Query("SELECT i FROM Expense i WHERE i.name = :name AND i.user = :user AND i.category = :category AND i.expenseDate > :date")
    List<Expense> findByNameAndUserAndCategoryAndExpenseDateAfter(
            @Param("name") String name,
            @Param("user") User user,
            @Param("category") Category category,
            @Param("date") LocalDate date
    );

    @Modifying
    @Query("DELETE FROM Expense i WHERE i.name = :name AND i.user = :user AND i.category = :category AND i.expenseDate > :date")
    void deleteByNameAndUserAndCategoryAndExpenseDateAfter(
            @Param("name") String name,
            @Param("user") User user,
            @Param("category") Category category,
            @Param("date") LocalDate date
    );

    List<Expense> findAllByExpenseDateBeforeOrExpenseDateEquals(LocalDate date, LocalDate date2);

    List<Expense> findAllByUserIdAndExpenseDateBefore(Long userId, LocalDate date);

    Page<Expense> findByUserId(Long id, Pageable page);

    List<Expense> findAllByNameIgnoreCaseAndUserId(String name, Long id);

}