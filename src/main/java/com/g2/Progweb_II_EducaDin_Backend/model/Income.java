package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "income")
public class Income implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
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

    @Enumerated(EnumType.STRING)
    @Column(name = "repeatable")
    private Repeatable repeatable;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString(){
        return String.format("""
                        Id: %d
                        Name: %s
                        Description: %s
                        Amount: %.2f
                        LeadTime: %d
                        Repeatable
                        Category: %s
                        Date: %s""",
                id,
                name,
                description,
                amount,
                leadTime,
                repeatable,
                category.toString(),
                incomeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
