package com.g2.Progweb_II_EducaDin_Backend.service;

import br.ueg.progweb2.arquitetura.model.dtos.AuthUserDTO;
import br.ueg.progweb2.arquitetura.service.CrudService;
import com.g2.Progweb_II_EducaDin_Backend.model.Income;
import com.g2.Progweb_II_EducaDin_Backend.model.User;

public interface UserService extends CrudService<User, Long> {
    User redefinePassword(AuthUserDTO authUserDTO);

    User getUserByEmail(String email);

    User getUserByUserName(String username);

    User getUserByLogin(String username);
}

