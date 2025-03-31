package com.g2.Progweb_II_EducaDin_Backend.repository;

import com.g2.Progweb_II_EducaDin_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    User findByEmail(String email);

    List<User> findAllByIdIn(List<Long> ids);
}
