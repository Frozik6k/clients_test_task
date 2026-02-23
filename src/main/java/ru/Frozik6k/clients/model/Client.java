package ru.Frozik6k.clients.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
    @Id
    @Column(name = "client_id", nullable = false, updatable = false)
    private UUID clientId;

    @Column(length = 64)
    private String name;

    @Column(name = "last_name", length = 64)
    private String lastName;

    @OneToMany(
            mappedBy = "client",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Contact> contacts;

    @PrePersist
    void prePersist() {
        if (clientId == null) clientId = UUID.randomUUID();
    }
}
