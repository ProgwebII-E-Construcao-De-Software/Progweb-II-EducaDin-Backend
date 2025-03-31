package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import com.g2.Progweb_II_EducaDin_Backend.mapper.IncomeMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ExpenseListDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${api.version}/incomes")
@CrossOrigin()
public class IncomeController extends GenericCRUDController<
        IncomeDTO,
        IncomeDTOCreateUpdate,
        IncomeDTOCreateUpdate,
        IncomeListDTO,
        Income,
        Long,
        IncomeService,
        IncomeMapper> {

    @PreAuthorize(value = "hasRole('ROLE_INCOME_READ')")
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
    public ResponseEntity<List<IncomeListDTO>> getByUserId(
            @Parameter(description = "Id do usuario")
            @PathVariable("id") Long id
    ) {
        List<IncomeListDTO> dtoResult = mapper.fromModelToDTOList(service.listAll(id));
        return ResponseEntity.ok(dtoResult);
    }
}
