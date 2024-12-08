package com.g2.Progweb_II_EducaDin_Backend.service.impl;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.hibernate.cfg.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String para, String titulo, String conteudo) {
        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);

        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        Thread emailThread = new Thread(() -> {
            try {
                javaMailSender.send(mensagem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        emailThread.start();
    }

    public void sendWithFile(String para, String titulo, String conteudo, String arquivo) throws MessagingException {
        var mensagem = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(para);
        helper.setSubject(titulo);
        helper.setText(conteudo, true);

        javaMailSender.send(mensagem);
    }
}
