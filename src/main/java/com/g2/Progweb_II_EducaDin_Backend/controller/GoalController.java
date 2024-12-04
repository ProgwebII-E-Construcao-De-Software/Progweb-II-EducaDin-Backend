package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import br.ueg.progweb2.arquitetura.model.dtos.CredencialDTO;
import com.g2.Progweb_II_EducaDin_Backend.mapper.GoalMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.*;
import com.g2.Progweb_II_EducaDin_Backend.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
