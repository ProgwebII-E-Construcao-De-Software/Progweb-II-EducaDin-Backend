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

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends GenericCrudService<User, Long, UserRepository> implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void prepareToCreate(User user) {
        String rawPassword = user.getLoginEnt().getPassword();
        user.setLoginEnt(new Login());
        user.getLoginEnt().setUser(user);
        user.getLoginEnt().setPassword(encodePassword(rawPassword));
        user.setRoles(getDefaultRoles());
        user.setActiveState(true);
    }

    @Override
    protected void validateBusinessLogicToCreate(User user) {
        validateEmail(user.getEmail());
        validateUniqueUser(user);
    }

    @Override
    protected void prepareToUpdate(User newUser, User existingUser) {
        if(newUser.getRoles().isEmpty() || existingUser.getRoles().isEmpty()){
            newUser.setRoles(getDefaultRoles());
            existingUser.setRoles(getDefaultRoles());
        }
        newUser.setActiveState(true);
        existingUser.setActiveState(true);
    }

    @Override
    protected void validateBusinessLogicToUpdate(User user) {
        if (Objects.isNull(getUserByEmail(user.getEmail()))) {
            throw new InvalidParameterException("email", "Usuário não encontrado.");
        }
        if (Objects.isNull(getUserByUserName(user.getLogin()))) {
            throw new InvalidParameterException("login", "Usuário não encontrado.");
        }
    }

    @Override
    protected void validateBusinessLogic(User user) {
        validateEmail(user.getEmail());
    }

    @Override
    public User redefinePassword(AuthUserDTO authUserDTO) {
        User user = getUser(authUserDTO);
        user.getLoginEnt().setPassword(encodePassword(authUserDTO.getNewPassword()));
        return repository.save(user);
    }

    private User getUser(AuthUserDTO authUserDTO) {
        User user = repository.findByEmail(authUserDTO.getEmail());
        if (Objects.isNull(user)) {
            throw new InvalidParameterException("email", "Usuário não encontrado.");
        }
        return user;
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

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    private List<String> getDefaultRoles() {
        return List.of(
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
                "ROLE_NOTIFICATIONPREFERENCE_READ_ALL",
                "ROLE_NOTIFICATIONPREFERENCE_CREATE",
                "ROLE_NOTIFICATIONPREFERENCE_READ",
                "ROLE_NOTIFICATIONPREFERENCE_UPDATE",
                "ROLE_NOTIFICATIONPREFERENCE_DELETE"
        );
    }

    private void validateEmail(String email) {
        if (!Util.isValidEmail(email)) {
            throw new InvalidParameterException("email", "O e-mail informado é inválido.");
        }
    }

    private void validateUniqueUser(User user) {
        if (Objects.nonNull(getUserByEmail(user.getEmail()))) {
            throw new InvalidParameterException("email", "E-mail já cadastrado.");
        }
        if (Objects.nonNull(getUserByUserName(user.getLogin()))) {
            throw new InvalidParameterException("login", "Login já em uso.");
        }
    }
}
