package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;

public interface GoalService extends CrudService<Goal, Long> {
    Goal share(Goal inputModel, Long idUser, Long idShare);
}
