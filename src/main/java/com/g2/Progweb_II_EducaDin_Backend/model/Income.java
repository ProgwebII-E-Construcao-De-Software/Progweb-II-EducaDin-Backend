package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.interfaces.ISearchFieldData;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.model.annotation.Searchable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.g2.Progweb_II_EducaDin_Backend.enums.Repeatable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "income")
public class Income implements GenericModel<Long>, ISearchFieldData<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Searchable(label = "id", autoComplete = true )
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    @Searchable(label = "nome", autoComplete = true )
    private String name;

    @MandatoryField(type = "String", name = "Description")
    @Column(name = "description", nullable = false)
    @Searchable(label = "descrição", autoComplete = true )
    private String description;

    @MandatoryField(type = "Double", name = "Amount")
    @Column(name = "amount", nullable = false)
    @Searchable(label = "valor", autoComplete = true )
    private Double amount;

    @MandatoryField(type = "Integer", name = "LeadTime")
    @Column(name = "indice")
    private Integer leadTime;

    @MandatoryField(type = "LocalDate", name = "incomeDate")
    @Searchable(label = "incomeDate", autoComplete = true )
    @Column(name = "data")
    private LocalDate incomeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeatable")
    @Searchable(label = "repetivel", autoComplete = true )
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

    @Override
    public String getSearchDescription() {
        return toString();
    }
}
