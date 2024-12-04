package com.g2.Progweb_II_EducaDin_Backend.mapper;

import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.Goal;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOCreate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalDTOUpdate;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.GoalListDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, UserMapper.class})
public interface GoalMapper extends GenericMapper<GoalDTO, GoalDTOCreate, GoalDTOUpdate, GoalListDTO, Goal, Long> {

    @Mapping(target = "user.id", source = "userId")
    Goal toModel(GoalDTOUpdate dto);

    @Mapping(target = "user.id", source = "userId")
    @Override
    Goal fromModelCreatedToModel(GoalDTOCreate dto);

    @Mapping(target = "userId", source = "user.id")
    @Override
    GoalDTO toDTO(Goal model);

    @Override
    @Named(value = "toDTOList")
    @Mapping(target = "goalPercent", expression = "java((100.0 * model.getAmountReached()) / model.getAmountTotal())")
    @Mapping(target = "userId", source = "user.id")
    GoalListDTO toDTOList(Goal model);

    @Override
    @Mapping(target = "user.id", source = "userId")
    Goal fromModelUpdatedToModel(GoalDTOUpdate goalDTOUpdate);
}
