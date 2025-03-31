package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.mapper.ExpenseMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.*;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
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
@RequestMapping(path = "1.0/expenses")
@CrossOrigin()
public class ExpenseController extends GenericCRUDController<
        ExpenseDTO,
        ExpenseDTOCreateUpdate,
        ExpenseDTOCreateUpdate,
        ExpenseListDTO,
        Expense,
        Long,
        ExpenseService,
        ExpenseMapper> {


    @PreAuthorize(value = "hasRole('ROLE_EXPENSE_READ')")
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
    public ResponseEntity<List<ExpenseListDTO>> getByUserId(
            @Parameter(description = "Id do usuario")
            @PathVariable("id") Long id
    ) {
        List<ExpenseListDTO> dtoResult = mapper.fromModelToDTOList(service.listAll(id));
        return ResponseEntity.ok(dtoResult);
    }

}

