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
public class PrenotazioneDTO {
    private Long id;
    private Long orarioId;
    private String orarioDescrizione;
    private Long utenteId;
    private String utenteUsername;
    private String stato;
    private Integer numeroPosti;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
