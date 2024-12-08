package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import br.ueg.progweb2.arquitetura.model.dtos.AuthUserDTO;
import br.ueg.progweb2.arquitetura.model.dtos.CredencialDTO;
import br.ueg.progweb2.arquitetura.service.IUserProviderService;
import com.g2.Progweb_II_EducaDin_Backend.mapper.UserMapper;
import com.g2.Progweb_II_EducaDin_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserProviderService implements IUserProviderService {

        @Autowired
        private UserMapper userMapper;

        @Autowired
        private UserService userService;

        @Autowired
        private EmailSenderService emailSenderService;

        @Override
        public CredencialDTO getCredentialByLogin(String username) {
            return userMapper.toCredentialDTO(userService.getUserByLogin(username));
        }

        @Override
        public CredencialDTO resetPassword(AuthUserDTO authUserDTO) {
            return userMapper.toCredentialDTO(userService.redefinePassword(authUserDTO));
        }

        @Override
        public CredencialDTO getCredentialByEmail(String email) {
            return userMapper.toCredentialDTO(userService.getUserByEmail(email));
        }

        @Override
        public boolean resetPasswordHelper(AuthUserDTO authUserDTO) {
            CredencialDTO userCredential = getCredentialByEmail(authUserDTO.getEmail());
            if (Objects.nonNull(userCredential)) {
                String newPlainPassword = RanderPassword.generatepassword();
                authUserDTO.setNewPassword(newPlainPassword);
                resetPassword(authUserDTO);
                emailSenderService.send(
                        authUserDTO.getEmail(),
                        "Definição de Senha",
                        "Olá, aqui é o Educadin!\n" +
                                "Sua senha de recuperação é: " + newPlainPassword +
                                "\nATENÇÃO: Mude sua senha após o primeiro acesso!"
                );
                return true;
            }
            return false;
        }

}
