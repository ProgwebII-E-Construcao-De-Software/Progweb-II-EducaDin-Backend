package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import com.g2.Progweb_II_EducaDin_Backend.mapper.ExpenseMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.*;
import com.g2.Progweb_II_EducaDin_Backend.service.ExpenseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.version}/incomes")
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
}

