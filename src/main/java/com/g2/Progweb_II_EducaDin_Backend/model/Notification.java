package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MandatoryField(name = "Type", type = "String")
    @Column(name = "type", nullable = false)
    private String type;

    @MandatoryField(name = "Message", type = "String")
    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
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
            UserId: %d,
            Type: %s,
            Message: %s,
            CreatedAt: %s""",
        id,
        user.getId(),
        type,
        message,
        createdAt);
    }
}