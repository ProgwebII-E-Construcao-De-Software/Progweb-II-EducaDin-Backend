package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "income")
public class Income implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @MandatoryField(type = "String", name = "Description")
    @Column(name = "description", nullable = false)
    private String description;

    @MandatoryField(type = "Double", name = "Price")
    @Column(name = "price", nullable = false)
    private Double price;

    @MandatoryField(type = "boolean", name = "fixed")
    @Column(name = "fixed")
    private boolean fixed;

    @MandatoryField(type = "Date", name = "date")
    @Column(name = "date")
    private LocalDate date;

    @Override
    public String toString(){
        return String.format("""
                        Id: %d
                        Name: %s
                        Description: %s
                        Price: %.2f
                        Is fixed: %s
                        Date: %s""",
                id,
                name,
                description,
                price,
                fixed,
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
