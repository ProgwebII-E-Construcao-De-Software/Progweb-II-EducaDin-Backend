package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.model.dtos.CredencialDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "user_")
@NoArgsConstructor
@AllArgsConstructor
public class User implements GenericModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @MandatoryField(name = "login", type = "String")
    @Column(name = "login")
    private String login;

    @MandatoryField(name = "email", type = "String")
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    @MandatoryField(name = "loginEnt", type = "Login")
    protected Login loginEnt;

    @MandatoryField(name = "roles", type = "List<String>")
    @Column(name = "roles")
    @ElementCollection
    private List<String> roles;

    @MandatoryField(name = "activeState", type = "boolean")
    @Column(name = "activeState")
    private boolean activeState;
}
