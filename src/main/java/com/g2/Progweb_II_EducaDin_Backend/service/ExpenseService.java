package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;

import java.util.List;

public interface ExpenseService extends CrudService<Expense, Long> {
    List<Expense> listAll(Long userId);
}
