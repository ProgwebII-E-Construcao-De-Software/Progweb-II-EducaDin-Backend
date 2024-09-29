package com.g2.Progweb_II_EducaDin_Backend.service;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import br.ueg.progweb2.arquitetura.service.CrudService;

import java.util.List;

public interface CategoryService extends CrudService<Category, Long> {
    boolean existsByName(String name);
    List<CategoryDTO> getCategoriesByIExpense(boolean IExpense);
}