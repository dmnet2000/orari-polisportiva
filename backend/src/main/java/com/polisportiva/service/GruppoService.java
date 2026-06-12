package com.polisportiva.service;

import com.polisportiva.dto.GruppoDTO;
import com.polisportiva.entity.Disciplina;
import com.polisportiva.entity.Gruppo;
import com.polisportiva.exception.ResourceNotFoundException;
import com.polisportiva.repository.DisciplinaRepository;
import com.polisportiva.repository.GruppoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GruppoService {

    @Autowired
    private GruppoRepository gruppoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<GruppoDTO> getAllGruppi() {
        return gruppoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<GruppoDTO> getGruppiByDisciplina(Long disciplinaId) {
        return gruppoRepository.findByDisciplinaId(disciplinaId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<GruppoDTO> getGruppiAttivi() {
        return gruppoRepository.findByAttivo(true).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public GruppoDTO getGruppoById(Long id) {
        Gruppo gruppo = gruppoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gruppo not found with id: " + id));
        return mapToDTO(gruppo);
    }

    public GruppoDTO createGruppo(GruppoDTO gruppoDTO) {
        Disciplina disciplina = disciplinaRepository.findById(gruppoDTO.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina not found with id: " + gruppoDTO.getDisciplinaId()));

        Gruppo gruppo = new Gruppo();
        gruppo.setNome(gruppoDTO.getNome());
        gruppo.setDescrizione(gruppoDTO.getDescrizione());
        gruppo.setDisciplina(disciplina);
        gruppo.setNumMembri(gruppoDTO.getNumMembri());
        gruppo.setIstruttore(gruppoDTO.getIstruttore());
        gruppo.setAttivo(true);

        Gruppo savedGruppo = gruppoRepository.save(gruppo);
        return mapToDTO(savedGruppo);
    }

    public GruppoDTO updateGruppo(Long id, GruppoDTO gruppoDTO) {
        Gruppo gruppo = gruppoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gruppo not found with id: " + id));

        if (gruppoDTO.getDisciplinaId() != null && !gruppo.getDisciplina().getId().equals(gruppoDTO.getDisciplinaId())) {
            Disciplina disciplina = disciplinaRepository.findById(gruppoDTO.getDisciplinaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Disciplina not found with id: " + gruppoDTO.getDisciplinaId()));
            gruppo.setDisciplina(disciplina);
        }

        gruppo.setNome(gruppoDTO.getNome());
        gruppo.setDescrizione(gruppoDTO.getDescrizione());
        gruppo.setNumMembri(gruppoDTO.getNumMembri());
        gruppo.setIstruttore(gruppoDTO.getIstruttore());
        gruppo.setAttivo(gruppoDTO.getAttivo());

        Gruppo updatedGruppo = gruppoRepository.save(gruppo);
        return mapToDTO(updatedGruppo);
    }

    public void deleteGruppo(Long id) {
        Gruppo gruppo = gruppoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gruppo not found with id: " + id));
        gruppoRepository.delete(gruppo);
    }

    private GruppoDTO mapToDTO(Gruppo gruppo) {
        return GruppoDTO.builder()
                .id(gruppo.getId())
                .nome(gruppo.getNome())
                .descrizione(gruppo.getDescrizione())
                .disciplinaId(gruppo.getDisciplina().getId())
                .disciplinaNome(gruppo.getDisciplina().getNome())
                .numMembri(gruppo.getNumMembri())
                .istruttore(gruppo.getIstruttore())
                .attivo(gruppo.getAttivo())
                .createdAt(gruppo.getCreatedAt())
                .updatedAt(gruppo.getUpdatedAt())
                .build();
    }
}
