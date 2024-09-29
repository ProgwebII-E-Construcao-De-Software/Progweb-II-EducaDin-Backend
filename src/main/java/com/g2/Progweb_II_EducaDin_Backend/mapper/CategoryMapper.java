package com.g2.Progweb_II_EducaDin_Backend.mapper;

import com.g2.Progweb_II_EducaDin_Backend.model.dto.CategoryDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.Category;
import br.ueg.progweb2.arquitetura.mapper.GenericMapper;

public interface CategoryMapper extends GenericMapper<
        CategoryDTO,
        CategoryDTO,
        CategoryDTO,
        CategoryDTO,
        Category,
        Long> {

}
