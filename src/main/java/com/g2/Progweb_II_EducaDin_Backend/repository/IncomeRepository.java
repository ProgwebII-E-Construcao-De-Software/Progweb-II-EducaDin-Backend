package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
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
public interface IncomeRepository extends JpaRepository<Income, Long>, JpaSpecificationExecutor<Income> {
    List<Income> findAllByName(String name);
    List<Income> findAllByUserId(long userId);
    List<Income> findAllByIncomeDateBeforeOrIncomeDateEquals(LocalDate date, LocalDate date2);
    List<Income> findAllByUserIdAndIncomeDateBefore(Long userId, LocalDate date);
    Page<Income> findByUserId(Long id, Pageable pageable);

    @Query("SELECT i FROM Income i WHERE i.name = :name AND i.user = :user AND i.category = :category AND i.incomeDate > :date")
    List<Income> findByNameAndUserAndCategoryAndIncomeDateAfter(
            @Param("name") String name,
            @Param("user") User user,
            @Param("category") Category category,
            @Param("date") LocalDate date
    );

    @Modifying
    @Query("DELETE FROM Income i WHERE i.name = :name AND i.user = :user AND i.category = :category AND i.incomeDate > :date")
    void deleteByNameAndUserAndCategoryAndIncomeDateAfter(
            @Param("name") String name,
            @Param("user") User user,
            @Param("category") Category category,
            @Param("date") LocalDate date
    );

    List<Income> findAllByNameIgnoreCaseAndUserId(String name, Long id);
}
