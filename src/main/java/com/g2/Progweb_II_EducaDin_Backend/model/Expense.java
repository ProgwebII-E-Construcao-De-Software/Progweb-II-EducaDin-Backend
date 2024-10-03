package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
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
@Table(name = "expense")
public class Expense implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="categoty_id")
    private Category category;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @MandatoryField(type = "String", name = "Description")
    @Column(name = "description", nullable = false)
    private String description;

    @MandatoryField(type = "Double", name = "Price")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @MandatoryField(type = "Integer", name = "LeadTime")
    @Column(name = "leadtime")
    private Integer leadTime;

    @MandatoryField(type = "boolean", name = "fixed")
    @Column(name = "fixed")
    private boolean fixed;

    @MandatoryField(type = "Date", name = "date")
    @Column(name = "date")
    private LocalDate date;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("""
                        Id: %d
                        Name: %s
                        Description: %s
                        Amount: %.2f
                        Is fixed: %s
                        Date: %s""",
                id,
                name,
                description,
                amount,
                fixed,
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}