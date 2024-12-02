package com.g2.Progweb_II_EducaDin_Backend.mapper;


import br.ueg.progweb2.arquitetura.mapper.GenericMapper;
import br.ueg.progweb2.arquitetura.model.dtos.AuthDTO;
import br.ueg.progweb2.arquitetura.model.dtos.AuthUserDTO;
import br.ueg.progweb2.arquitetura.model.dtos.CredencialDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.UserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends GenericMapper<CredencialDTO, UserCreateDTO, AuthUserDTO, CredencialDTO, User, Long> {

    @Mapping(source = "password", target = "loginEnt.password")
    User fromAuthDTOToModel(AuthDTO authDTO);

    @Mapping(source = "password", target = "loginEnt.password")
    User fromCreateDTOToModel(UserCreateDTO dto);

    CredencialDTO toCredentialDTO(User user);
}