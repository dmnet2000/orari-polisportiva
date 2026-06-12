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
public class PalestraDTO {
    private Long id;
    private String nome;
    private String descrizione;
    private String indirizzo;
    private String citta;
    private String cap;
    private String telefono;
    private String email;
    private Integer numCampi;
    private Boolean attiva;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
