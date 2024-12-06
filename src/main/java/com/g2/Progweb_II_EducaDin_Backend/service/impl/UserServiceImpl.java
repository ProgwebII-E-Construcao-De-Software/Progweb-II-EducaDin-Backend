package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.exceptions.InvalidParameterException;
import br.ueg.progweb2.arquitetura.model.dtos.AuthUserDTO;
import br.ueg.progweb2.arquitetura.service.impl.GenericCrudService;
import br.ueg.progweb2.arquitetura.util.Util;
import com.g2.Progweb_II_EducaDin_Backend.model.Login;
import com.g2.Progweb_II_EducaDin_Backend.model.User;
import com.g2.Progweb_II_EducaDin_Backend.repository.UserRepository;
import com.g2.Progweb_II_EducaDin_Backend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class UserServiceImpl extends GenericCrudService<User, Long, UserRepository> implements UserService {





    @Override
    protected void prepareToCreate(User dado) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        var key =  dado.getLoginEnt().getPassword();
        dado.setLoginEnt(new Login());
        dado.getLoginEnt().setUser(dado);
        dado.getLoginEnt().setPassword(bCryptPasswordEncoder.encode(key));
        dado.setRoles(Arrays.asList(
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
                "ROLE_GOAL_READ_ALL",
                "ROLE_CATEGORY_REMOVEALL",
                "ROLE_CATEGORY_CREATE",
                "ROLE_CATEGORY_READ",
                "ROLE_CATEGORY_UPDATE",
                "ROLE_CATEGORY_DELETE",
                "ROLE_CATEGORY_READ_ALL",
                "ROLE_NOTIFICATION_READ_ALL",
                "ROLE_NOTIFICATION_CREATE",
                "ROLE_NOTIFICATION_READ",
                "ROLE_NOTIFICATION_UPDATE",
                "ROLE_NOTIFICATION_DELETE",
                "ROLE_NOTIFICATION_READ_ALL",
                "ROLE_NOTIFICATIONPREFERENCE_READ_ALL",
                "ROLE_NOTIFICATIONPREFERENCE_CREATE",
                "ROLE_NOTIFICATIONPREFERENCE_READ",
                "ROLE_NOTIFICATIONPREFERENCE_UPDATE",
                "ROLE_NOTIFICATIONPREFERENCE_DELETE",
                "ROLE_NOTIFICATIONPREFERENCE_READ_ALL"));
        dado.setActiveState(true);

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
        newModel.setRoles(Arrays.asList(
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
        ));
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

    @Override
    public User getUserByLogin(String username) {
        return repository.findByLogin(username);
    }
}
