package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationPreferenceRepository extends JpaRepository<NotificationPreference, Long> {
    List<NotificationPreference> findByUserId(Long userId);

    boolean existsByUserIdAndType(Long userId, String type);
    List<NotificationPreference> findAllByUserId(long userId);
}