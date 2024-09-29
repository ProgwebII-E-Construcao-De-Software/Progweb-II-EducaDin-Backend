package com.g2.Progweb_II_EducaDin_Backend.controller;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.service.CategoryService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.CategoryMapper;
import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${api.version}/categories")
@CrossOrigin()
public class CategoryController extends GenericCRUDController<
        CategoryDTO,
        CategoryDTO,
        CategoryDTO,
        CategoryDTO,
        Category,
        Long,
        CategoryService,
        CategoryMapper> {

    @GetMapping("/incomes")
    @Operation(description = "End point para listar todas as categorias de receitas")
    public ResponseEntity<List<CategoryDTO>> getIncomeCategories() {
        List<CategoryDTO> incomeCategories = service.getCategoriesByIExpense(false);
        return ResponseEntity.ok(incomeCategories);
    }

    @GetMapping("/expenses")
    @Operation(description = "End point para listar todas as categorias de despesas")
    public ResponseEntity<List<CategoryDTO>> getExpenseCategories() {
        List<CategoryDTO> expenseCategories = service.getCategoriesByIExpense(true);
        return ResponseEntity.ok(expenseCategories);
    }
}