package com.polisportiva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrarioDTO {
    private Long id;
    private Long palestraId;
    private String palestraNome;
    private Long gruppoId;
    private String gruppoNome;
    private Integer numeroCampo;
    private String giornoSettimana;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private Integer capienzaMassima;
    private Integer postiDisponibili;
    private Boolean attivo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
