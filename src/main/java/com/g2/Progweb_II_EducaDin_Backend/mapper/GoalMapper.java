package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOCreate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalListDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface GoalMapper extends GenericMapper<GoalDTO, GoalDTOCreate, GoalDTOUpdate, GoalListDTO, Goal, Long> {

    Goal fromDTOtoModel(GoalDTOUpdate dto);

    @Override
    Goal fromDTOCreateToModel(GoalDTOCreate dto);

    @Override
    GoalDTO fromModeltoDTO(Goal model);

    @Override
    @Named(value = "toDTOList")
    @Mapping(target = "goalPercent", expression = "java((100.0 * model.getAmountReached()) / model.getAmountTotal())")
    GoalListDTO toDTOList(Goal model);

    @Override
    Goal fromDTOUpdateToModel(GoalDTOUpdate goalDTOUpdate);
}
