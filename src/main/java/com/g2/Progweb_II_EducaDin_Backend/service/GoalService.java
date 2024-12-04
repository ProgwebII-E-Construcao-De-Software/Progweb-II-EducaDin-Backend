package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;

import java.util.List;

public interface GoalService extends CrudService<Goal, Long> {
    List<Goal> listAll(Long userId);
}
