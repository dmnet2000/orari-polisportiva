package com.polisportiva.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GruppoDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private Long disciplinaId;
    private String disciplinaNome;
    private Integer numMembri;
    private String istruttore;
    private Boolean attivo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
