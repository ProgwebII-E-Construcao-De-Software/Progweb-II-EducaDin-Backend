package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.ReportGeneralDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${api.version}/report")
@CrossOrigin()
public class ReportController {
    @Autowired
    ReportService reportService;
    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Obter os dados completos do relatório pelo id informado!", responses = {
            @ApiResponse(responseCode = "200", description = "Dados encontrados",
                    useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Dados não encontrados",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<ReportGeneralDTO> getById(
            @PathVariable("id") Long id
    ) {
        ReportGeneralDTO dtoResult = (reportService.getGeneralBalanceData(id));
        return ResponseEntity.ok(dtoResult);
    }
}
