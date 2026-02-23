package ru.Frozik6k.clients.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(length = 16)
    private String phone;

    @Column(length = 64)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @PrePersist
    void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }
}
