package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.annotation.Searchable;
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
@Builder
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
    @Searchable(label = "name", autoComplete = true )
    private String name;

    @MandatoryField(type = "String", name = "Description")
    @Column(name = "description", nullable = false)
    @Searchable(label = "description", autoComplete = true )
    private String description;

    @MandatoryField(type = "Double", name = "Price")
    @Column(name = "amount", nullable = false)
    @Searchable(label = "amount", autoComplete = true )
    private Double amount;

    @MandatoryField(type = "Integer", name = "LeadTime")
    @Column(name = "leadtime")
    @Searchable(label = "leadtime", autoComplete = true )
    private Integer leadTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeatable")
    private Repeatable repeatable;

    @MandatoryField(type = "Date", name = "date")
    @Column(name = "date")
    @Searchable(label = "expenseDate", autoComplete = true )
    private LocalDate expenseDate;

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
                        Date: %s""",
                id,
                name,
                description,
                amount,
                leadTime,
                expenseDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}