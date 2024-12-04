package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import br.ueg.progweb2.arquitetura.model.annotation.Searchable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "goal")
public class Goal implements GenericModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Searchable(label = "id", autoComplete = true )
    private Long id;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    @Searchable(label = "name", autoComplete = true )
    private String name;

    @Column(name = "amount_reached")
    @Searchable(label = "amountReached", autoComplete = true )
    private Double amountReached;

    @MandatoryField(type = "Double", name = "Amount")
    @Searchable(label = "amountTotal", autoComplete = true )
    @Column(name = "amount_total", nullable = false)
    private Double amountTotal;

    @MandatoryField(type = "LocalDate", name = "goal_date")
    @Searchable(label = "goalDate", autoComplete = true )
    @Column(name = "goal_date", nullable = false)
    private LocalDate goalDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

