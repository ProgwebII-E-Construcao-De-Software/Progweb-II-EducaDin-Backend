package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.model.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField(name = "Name", type = "String")
    @Column(nullable = false)
    private String name;

    @MandatoryField(name = "IExpense", type = "boolean")
    @Column(nullable = false)
    private boolean IExpense;

    public boolean getIExpense() {
        return IExpense;
    }

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
                    Name: %s,
                    IExpense: %b""",
                id,
                name,
                IExpense);
    }

}