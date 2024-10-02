package com.g2.Progweb_II_EducaDin_Backend.model.dto;


import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "model_test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelTest implements GenericModel<Long> {

    @Id
    Long id;

    @MandatoryField(name = "name", type = "String")
    @Column(nullable = false)
    String nome;

    @MandatoryField(name = "description", type = "String")
    @Column(nullable = false)
    String description;

    @Column()
    String nick;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }
}