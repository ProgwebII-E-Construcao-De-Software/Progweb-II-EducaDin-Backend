package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import com.g2.Progweb_II_EducaDin_Backend.mapper.IncomeMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.IncomeListDTO;
import com.g2.Progweb_II_EducaDin_Backend.service.IncomeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
