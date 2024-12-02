package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tip")
public class Tip implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField(name = "Type", type = "String")
    @Column(name = "type", nullable = false)
    private String type;

    @MandatoryField(name = "Message", type = "String")
    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("""
                    Id: %d,
                    Type: %s""",
                id,
                type);
    }
}