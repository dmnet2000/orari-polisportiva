package com.polisportiva.service;

import com.polisportiva.dto.DisciplinaDTO;
import com.polisportiva.entity.Disciplina;
import com.polisportiva.exception.ResourceNotFoundException;
import com.polisportiva.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<DisciplinaDTO> getAllDiscipline() {
        return disciplinaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<DisciplinaDTO> getDisciplineAttive() {
        return disciplinaRepository.findByAttiva(true).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public DisciplinaDTO getDisciplinaById(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina not found with id: " + id));
        return mapToDTO(disciplina);
    }

    public DisciplinaDTO createDisciplina(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setDescrizione(disciplinaDTO.getDescrizione());
        disciplina.setNumGiocatoriMin(disciplinaDTO.getNumGiocatoriMin());
        disciplina.setNumGiocatoriMax(disciplinaDTO.getNumGiocatoriMax());
        disciplina.setAttiva(true);

        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);
        return mapToDTO(savedDisciplina);
    }

    public DisciplinaDTO updateDisciplina(Long id, DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina not found with id: " + id));

        disciplina.setNome(disciplinaDTO.getNome());
        disciplina.setDescrizione(disciplinaDTO.getDescrizione());
        disciplina.setNumGiocatoriMin(disciplinaDTO.getNumGiocatoriMin());
        disciplina.setNumGiocatoriMax(disciplinaDTO.getNumGiocatoriMax());
        disciplina.setAttiva(disciplinaDTO.getAttiva());

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return mapToDTO(updatedDisciplina);
    }

    public void deleteDisciplina(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina not found with id: " + id));
        disciplinaRepository.delete(disciplina);
    }

    private DisciplinaDTO mapToDTO(Disciplina disciplina) {
        return DisciplinaDTO.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .descrizione(disciplina.getDescrizione())
                .numGiocatoriMin(disciplina.getNumGiocatoriMin())
                .numGiocatoriMax(disciplina.getNumGiocatoriMax())
                .attiva(disciplina.getAttiva())
                .createdAt(disciplina.getCreatedAt())
                .updatedAt(disciplina.getUpdatedAt())
                .build();
    }
}
