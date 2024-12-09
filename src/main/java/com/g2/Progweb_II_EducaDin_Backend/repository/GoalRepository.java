package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> , JpaSpecificationExecutor<Goal> {
    Goal findByNameEqualsIgnoreCase(String name);
    List<Goal> findAllByUserId(long userId);

    /**
     * Busca metas do proprietário e metas compartilhadas com ele.
     *
     * @param userId ID do usuário.
     * @return Lista de metas.
     */
    @Query("SELECT g FROM Goal g " +
            "WHERE g.owner.id = :userId " +
            "   OR :userId IN (SELECT sharedUser.id FROM g.sharedUsers sharedUser)")
    List<Goal> findGoalsByOwnerOrSharedWith(Long userId);
}
