package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private Long id;

    @MandatoryField(type = "String", name = "Name")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount_reached")
    private Double amountReached;

    @MandatoryField(type = "Double", name = "Amount")
    @Column(name = "amount_total", nullable = false)
    private Double amountTotal;

    @MandatoryField(type = "LocalDate", name = "goal_date")
    @Column(name = "goal_date", nullable = false)
    private LocalDate goalDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

