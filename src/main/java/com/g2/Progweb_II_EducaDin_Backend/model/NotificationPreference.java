package com.g2.Progweb_II_EducaDin_Backend.model;

import br.ueg.progweb2.arquitetura.annotations.MandatoryField;
import br.ueg.progweb2.arquitetura.model.GenericModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification_preference")
public class NotificationPreference implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MandatoryField(name = "UserId", type = "Long")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @MandatoryField(name = "Type", type = "String")
    @Column(name = "type", nullable = false)
    private String type;

    @MandatoryField(name = "Enabled", type = "boolean")
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public boolean getEnabled() {
        return isEnabled();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
                    Enabled: %b""",
                id,
                userId,
                type,
                enabled);
    }
}