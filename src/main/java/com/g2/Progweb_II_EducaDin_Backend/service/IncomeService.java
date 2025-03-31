package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;

import java.util.List;

public interface IncomeService extends CrudService<Income, Long> {
    List<Income> listAll(Long userId);
}
