package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Tip;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.TipDTO;

public interface TipService extends CrudService<Tip, Long> {

    TipDTO getRandomTipByType(String type, Long userId);
}
