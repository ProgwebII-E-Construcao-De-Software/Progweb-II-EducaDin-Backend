package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import br.ueg.progweb2.arquitetura.exceptions.ApiMessageCode;
import br.ueg.progweb2.arquitetura.exceptions.BusinessException;
import br.ueg.progweb2.arquitetura.exceptions.MessageResponse;
import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import br.ueg.progweb2.arquitetura.model.dtos.SearchFieldValue;
import com.g2.Progweb_II_EducaDin_Backend.mapper.ExpenseMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.*;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${api.version}/expenses")
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

    @PreAuthorize(value = "hasRole(#root.this.getRoleName(#root.this.ROLE_READ_ALL))")
    @GetMapping(
            path = "/page/user/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "lista todos modelos paginada", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral paginada",
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
    public ResponseEntity<Page<ExpenseListDTO>> listAllPageByUser(@PageableDefault(page = 0, size = 5) Pageable page,
                                                                 @Parameter(description = "Id do usuario") @PathVariable("id") Long id ){
        Page<Expense> pageEntidade = service.listAllByIdPage(id, page);
        return ResponseEntity.ok(mapPageEntityToDto(pageEntidade));
    }

    @PostMapping(path = "/search-fields/page/user/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Realiza a busca pelos valores dos campos informados", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem do resultado",
                    useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "falha ao realizar a busca",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<Page<ExpenseListDTO>> searchFieldsActionPage(
            @RequestBody List<SearchFieldValue> searchFieldValues,
            @RequestParam(name = "page", defaultValue = "0", required = false)  Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false)  Integer size,
            @RequestParam(name = "sort", defaultValue = "", required = false)  List<String> sort,
            @Parameter(description = "Id do usuario") @PathVariable("id") Long id
    ){
        Sort sortObject = Sort.unsorted();
        if(Objects.nonNull(sort)){
            List<Sort.Order> orderList = new ArrayList<>();
            sort.forEach(s -> orderList.add(Sort.Order.asc(s)));
            sortObject = Sort.by(orderList);
        }
        Pageable pageable = PageRequest.of(page, size, sortObject);
        Page<Expense> listSearchFields = service.searchFieldValuesPage(pageable, searchFieldValues);

        if(listSearchFields.isEmpty()){
            throw new BusinessException(ApiMessageCode.SEARCH_FIELDS_RESULT_NONE);
        }
        return ResponseEntity.ok(new PageImpl<>(this.mapPageEntityToDto(listSearchFields).stream().filter(value -> value.userId().equals(id)).toList()));
    }

}

