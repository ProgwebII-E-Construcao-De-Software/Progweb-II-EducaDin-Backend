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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize(value = "hasRole('ROLE_GOAL_READ')")
    @GetMapping(path = "/user/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Obter os dados completos de uma entidiade pelo id do usuario informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Entidade encontrada",
                    useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<List<GoalListDTO>> getByUserId(
            @Parameter(description = "Id do usuario")
            @PathVariable("id") Long id
    ) {
        List<GoalListDTO> dtoResult = mapper.fromModelToDTOList(service.listAll(id));
        return ResponseEntity.ok(dtoResult);
    }
}
