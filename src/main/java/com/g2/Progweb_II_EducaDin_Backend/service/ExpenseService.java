package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.model.dtos.SearchFieldValue;
import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import com.g2.Progweb_II_EducaDin_Backend.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService extends CrudService<Expense, Long> {
    List<Expense> listAll(Long userId);

    Page<Expense> listAllByIdPage(Long id, Pageable page);

}
