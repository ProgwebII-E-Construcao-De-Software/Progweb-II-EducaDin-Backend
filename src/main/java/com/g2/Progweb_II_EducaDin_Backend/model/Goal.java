package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.interfaces.ISearchFieldData;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.model.annotation.Searchable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "goal")
public class Goal implements GenericModel<Long>, ISearchFieldData<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Searchable(label = "id", autoComplete = true )
    private Long id;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    @Searchable(label = "nome", autoComplete = true )
    private String name;

    @Column(name = "amount_reached")
    @Searchable(label = "valor alcançado", autoComplete = true )
    private Double amountReached;

    @MandatoryField(type = "Double", name = "Amount")
    @Searchable(label = "valor final", autoComplete = true )
    @Column(name = "amount_total", nullable = false)
    private Double amountTotal;

    @MandatoryField(type = "LocalDate", name = "goal_date")
    @Searchable(label = "goalDate", autoComplete = true )
    @Column(name = "data", nullable = false)
    private LocalDate goalDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MandatoryField(type = "List<Long>", name = "SharedWith")
    @ElementCollection
    @CollectionTable(name = "goal_shared_with", joinColumns = @JoinColumn(name = "goal_id"))
    @Column(name = "user_id")
    private List<Long> sharedWith;

    @Override
    public String toString(){
        return String.format("""
                        Id: %d
                        Name: %s
                        Amount Reached: %.2f
                        Amount Total: %.2f
                        Date: %s
                        user: %d""",
                id,
                name,
                amountReached,
                amountTotal,
                goalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                user.getId());
    }

    @Override
    public String getSearchDescription() {
        return toString();
    }
}

