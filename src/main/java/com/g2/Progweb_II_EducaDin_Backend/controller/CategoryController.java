package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${api.version}/categories")
@CrossOrigin()
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;



    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/incomes/{id}")
    @Operation(description = "End point para listar todas as categorias de receitas")
    public ResponseEntity<List<CategoryDTO>> getIncomeCategories(
            @Parameter(description = "Id do usuario")
            @PathVariable("id") Long id
    ){
        List<CategoryDTO> incomeCategories = categoryService.getCategoriesByIExpenseAndUserId(false, id);
        return ResponseEntity.ok(incomeCategories);
    }

    @GetMapping("/expenses/{id}")
    @Operation(description = "End point para listar todas as categorias de despesas")
    public ResponseEntity<List<CategoryDTO>> getExpenseCategories(
            @Parameter(description = "Id do usuario")
            @PathVariable("id") Long id
    ) {
        List<CategoryDTO> expenseCategories = categoryService.getCategoriesByIExpenseAndUserId(true, id);
        return ResponseEntity.ok(expenseCategories);
    }

    @PreAuthorize(value = "hasRole('ROLE_CATEGORY_READ')")
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
    public ResponseEntity<List<CategoryDTO>> getByUserId(
            @Parameter(description = "Id do usuario")
            @PathVariable("id") Long id
    ) {
        List<CategoryDTO> dtoResult = categoryMapper.fromModelToDTOList(categoryService.listAll(id));
        return ResponseEntity.ok(dtoResult);
    }

}