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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
  
    private Category category;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @MandatoryField(type = "String", name = "Description")
    @Column(name = "description", nullable = false)
    private String description;

    @MandatoryField(type = "Double", name = "Amount")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @MandatoryField(type = "Integer", name = "LeadTime")
    @Column(name = "leadtime")
    private Integer leadTime;

    @MandatoryField(type = "LocalDate", name = "incomeDate")
    @Column(name = "incomeDate")
    private LocalDate incomeDate;

    @Override
    public String toString(){
        return String.format("""
                        Id: %d
                        Name: %s
                        Description: %s
                        Amount: %.2f
                        LeadTime: %d
                        Date: %s""",
                id,
                name,
                description,
                amount,
                leadTime,
                incomeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
