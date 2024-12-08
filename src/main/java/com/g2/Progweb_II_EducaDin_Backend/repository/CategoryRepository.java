package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndUserId(final String name, Long userId);
    List<Category> findByIExpenseAndUserId(boolean iExpense, Long userId);
    Category findByNameEqualsIgnoreCaseAndUserId(final String name, Long userId);
    List<Category> findAllByUserId(long userId);
}