package com.g2.Progweb_II_EducaDin_Backend.model.dto;

import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.model.MandatoryField;
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

    @MandatoryField(name = "nome", type = "String")
    @Column(nullable = false)
    String nome;

    @MandatoryField(name = "nome", type = "String")
    @Column(nullable = false)
    String descricao;

    @Column()
    String apelido;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }
}
