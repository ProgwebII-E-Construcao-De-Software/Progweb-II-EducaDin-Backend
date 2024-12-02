package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.model.dtos.AuthDTO;
import br.ueg.progweb2.arquitetura.model.dtos.AuthUserDTO;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import br.ueg.progweb2.arquitetura.util.Util;
import com.g2.Progweb_II_EducaDin_Backend.AppStartupRunner;
import com.g2.Progweb_II_EducaDin_Backend.model.Login;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Logger;

@Service
public class UserServiceImpl extends GenericCrudService<User, Long, UserRepository> implements UserService {



    @Override
    protected void prepareToCreate(User dado) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        var key =  dado.getLoginEnt().getPassword();
        dado.setLoginEnt(new Login());
        dado.getLoginEnt().setUser(dado);
        dado.getLoginEnt().setPassword(bCryptPasswordEncoder.encode(key));

    }

    @Override
    protected void validateBusinessLogicToCreate(User dado) {
        if(!Util.isValidEmail(dado.getEmail())){
            throw new InvalidParameterException("email", "O email é inválido");
        }
        if(Objects.nonNull(getUserByEmail(dado.getEmail()))){
            throw new InvalidParameterException("", "Email ou senha inválidos");
        }
        if(Objects.nonNull(getUserByUserName(dado.getLogin()))){
            throw new InvalidParameterException("login", "Login em uso");
        }
    }

    @Override
    protected void prepareToUpdate(User newModel, User model) {

    }

    @Override
    protected void validateBusinessLogicToUpdate(User dado) {
        if(Objects.isNull(getUserByEmail(dado.getEmail()))){
            throw new InvalidParameterException("", "Usuário não encontrado");
        }
        if(Objects.isNull(getUserByUserName(dado.getLogin()))){
            throw new InvalidParameterException("", "Usuário não encontrado");
        }
    }

    @Override
    protected void validateBusinessLogic(User dado) {

    }

    @Override
    public User redefinePassword(AuthUserDTO authUserDTO) {
        User user = repository.findByEmail(authUserDTO.getEmail());
        validateBusinessLogicToUpdate(user);
        user.getLoginEnt().setPassword(authUserDTO.getNewPassword());
        return update(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User getUserByUserName(String username) {
        return repository.findByLogin(username);
    }
}
