package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
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

    @MandatoryField(name = "UserId", type = "Long")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @MandatoryField(name = "Type", type = "String")
    @Column(name = "type", nullable = false)
    private String type; // Ex.: "TIP", "META_FIM", "LEMBRETE"

    @MandatoryField(name = "Message", type = "String")
    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @MandatoryField(name = "Read", type = "boolean")
    @Column(name = "is_read", nullable = false)
    private boolean read = false;

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
}