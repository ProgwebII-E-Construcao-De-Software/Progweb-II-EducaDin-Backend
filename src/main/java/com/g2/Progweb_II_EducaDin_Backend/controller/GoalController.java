package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import com.g2.Progweb_II_EducaDin_Backend.mapper.GoalMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOCreate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalListDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(
            path = "/{idUser}/{idShare}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(description = "Método utilizado para compartilhar uma meta",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Meta Compartilhada",
                            useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "403", description = "Acesso negado",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MessageResponse.class)))
            })
    public ResponseEntity<GoalDTO> share(@RequestBody GoalDTO dto, @PathVariable("idUser") Long idUser, @PathVariable("idShare") Long idShare) {
        Goal inputModel = mapper.fromDTOtoModel(dto);
        GoalDTO resultDTO = mapper.fromModeltoDTO(service.share(inputModel, idUser, idShare));
        return ResponseEntity.ok(resultDTO);
    }
}
