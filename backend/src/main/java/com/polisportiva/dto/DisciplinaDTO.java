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
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private Integer numGiocatoriMin;
    private Integer numGiocatoriMax;
    private Boolean attiva;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
