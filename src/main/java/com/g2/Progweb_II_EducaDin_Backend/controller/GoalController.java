package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import com.g2.Progweb_II_EducaDin_Backend.mapper.GoalMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOCreate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalListDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.GoalService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.version}/goals")
@CrossOrigin()
public class GoalController extends GenericCRUDController<
        GoalDTO,
        GoalDTOCreate,
        GoalDTOUpdate,
        GoalListDTO,
        Goal,
        Long,
        GoalService,
        GoalMapper> {
}
