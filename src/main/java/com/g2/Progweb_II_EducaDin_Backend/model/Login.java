package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "user", callSuper = false)
@Entity
@Table(name = "login")
public class Login {

    @Id
    @Column(name="user_id")
    protected Long id;


    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference
    private  User user;

    @Column(name="password")
    @MandatoryField(name = "password", type = "String")
    protected String password;
}