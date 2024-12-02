package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.model.dtos.AuthUserDTO;
import br.ueg.progweb2.arquitetura.model.dtos.CredencialDTO;
import br.ueg.progweb2.arquitetura.service.IUserProviderService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.UserMapper;
import com.g2.Progweb_II_EducaDin_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserProviderService implements IUserProviderService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Override
    public CredencialDTO getCredentialByLogin(String username) {
        return userMapper.toCredentialDTO(this.userService.getUserByLogin(username));
    }

    private static CredencialDTO getCredencialDTO() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode("admin");
        return CredencialDTO.builder()
                .login("admin")
                .id(1L)
                .name("Admin")
                .email("admin@admin.com.br")
                .roles(Arrays.asList(
                        "ROLE_INCOME_REMOVEALL",
                        "ROLE_INCOME_CREATE",
                        "ROLE_INCOME_READ",
                        "ROLE_INCOME_UPDATE",
                        "ROLE_INCOME_DELETE",
                        "ROLE_INCOME_READ_ALL",
                        "ROLE_EXPENSE_REMOVEALL",
                        "ROLE_EXPENSE_CREATE",
                        "ROLE_EXPENSE_READ",
                        "ROLE_EXPENSE_UPDATE",
                        "ROLE_EXPENSE_DELETE",
                        "ROLE_EXPENSE_READ_ALL",
                        "ROLE_GOAL_REMOVEALL",
                        "ROLE_GOAL_CREATE",
                        "ROLE_GOAL_READ",
                        "ROLE_GOAL_UPDATE",
                        "ROLE_GOAL_DELETE",
                        "ROLE_GOAL_READ_ALL"
                ))
                .activeState(true)
                .password(encodedPassword)
                .build();
    }

    @Override
    public CredencialDTO resetPassword(AuthUserDTO authUserDTO) {
        return userMapper.toCredentialDTO(this.userService.redefinePassword(authUserDTO));
    }

    @Override
    public CredencialDTO getCredentialByEmail(String email) {
         return userMapper.toCredentialDTO(this.userService.getUserByEmail(email));
    }
}
