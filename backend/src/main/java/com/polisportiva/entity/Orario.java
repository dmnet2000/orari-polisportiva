package com.polisportiva.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orari")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "palestra_id", nullable = false)
    private Palestra palestra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gruppo_id", nullable = false)
    private Gruppo gruppo;

    @Column(name = "numero_campo")
    private Integer numeroCampo;

    @Column(name = "giorno_settimana", nullable = false)
    private String giornoSettimana; // MONDAY, TUESDAY, etc.

    @Column(name = "ora_inizio", nullable = false)
    private LocalTime oraInizio;

    @Column(name = "ora_fine", nullable = false)
    private LocalTime oraFine;

    @Column(name = "capienza_massima")
    private Integer capienzaMassima;

    @Column(name = "posti_disponibili")
    private Integer postiDisponibili;

    @Column(columnDefinition = "boolean default true")
    private Boolean attivo = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "orario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
