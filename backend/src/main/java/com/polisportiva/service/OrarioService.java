package com.polisportiva.service;

import com.polisportiva.dto.OrarioDTO;
import com.polisportiva.entity.Gruppo;
import com.polisportiva.entity.Orario;
import com.polisportiva.entity.Palestra;
import com.polisportiva.exception.ResourceNotFoundException;
import com.polisportiva.repository.GruppoRepository;
import com.polisportiva.repository.OrarioRepository;
import com.polisportiva.repository.PalestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrarioService {

    @Autowired
    private OrarioRepository orarioRepository;

    @Autowired
    private PalestraRepository palestraRepository;

    @Autowired
    private GruppoRepository gruppoRepository;

    public List<OrarioDTO> getAllOrari() {
        return orarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrarioDTO> getOrariByPalestra(Long palestraId) {
        return orarioRepository.findByPalestraId(palestraId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrarioDTO> getOrariByGruppo(Long gruppoId) {
        return orarioRepository.findByGruppoId(gruppoId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrarioDTO> getOrariByGiorno(String giorno) {
        return orarioRepository.findByGiornoSettimana(giorno).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<OrarioDTO> getOrariAttivi() {
        return orarioRepository.findByAttivo(true).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public OrarioDTO getOrarioById(Long id) {
        Orario orario = orarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orario not found with id: " + id));
        return mapToDTO(orario);
    }

    public OrarioDTO createOrario(OrarioDTO orarioDTO) {
        Palestra palestra = palestraRepository.findById(orarioDTO.getPalestraId())
                .orElseThrow(() -> new ResourceNotFoundException("Palestra not found with id: " + orarioDTO.getPalestraId()));

        Gruppo gruppo = gruppoRepository.findById(orarioDTO.getGruppoId())
                .orElseThrow(() -> new ResourceNotFoundException("Gruppo not found with id: " + orarioDTO.getGruppoId()));

        Orario orario = new Orario();
        orario.setPalestra(palestra);
        orario.setGruppo(gruppo);
        orario.setNumeroCampo(orarioDTO.getNumeroCampo());
        orario.setGiornoSettimana(orarioDTO.getGiornoSettimana());
        orario.setOraInizio(orarioDTO.getOraInizio());
        orario.setOraFine(orarioDTO.getOraFine());
        orario.setCapienzaMassima(orarioDTO.getCapienzaMassima());
        orario.setPostiDisponibili(orarioDTO.getCapienzaMassima());
        orario.setAttivo(true);

        Orario savedOrario = orarioRepository.save(orario);
        return mapToDTO(savedOrario);
    }

    public OrarioDTO updateOrario(Long id, OrarioDTO orarioDTO) {
        Orario orario = orarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orario not found with id: " + id));

        if (orarioDTO.getPalestraId() != null && !orario.getPalestra().getId().equals(orarioDTO.getPalestraId())) {
            Palestra palestra = palestraRepository.findById(orarioDTO.getPalestraId())
                    .orElseThrow(() -> new ResourceNotFoundException("Palestra not found with id: " + orarioDTO.getPalestraId()));
            orario.setPalestra(palestra);
        }

        if (orarioDTO.getGruppoId() != null && !orario.getGruppo().getId().equals(orarioDTO.getGruppoId())) {
            Gruppo gruppo = gruppoRepository.findById(orarioDTO.getGruppoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Gruppo not found with id: " + orarioDTO.getGruppoId()));
            orario.setGruppo(gruppo);
        }

        orario.setNumeroCampo(orarioDTO.getNumeroCampo());
        orario.setGiornoSettimana(orarioDTO.getGiornoSettimana());
        orario.setOraInizio(orarioDTO.getOraInizio());
        orario.setOraFine(orarioDTO.getOraFine());
        orario.setCapienzaMassima(orarioDTO.getCapienzaMassima());
        orario.setAttivo(orarioDTO.getAttivo());

        Orario updatedOrario = orarioRepository.save(orario);
        return mapToDTO(updatedOrario);
    }

    public void deleteOrario(Long id) {
        Orario orario = orarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orario not found with id: " + id));
        orarioRepository.delete(orario);
    }

    private OrarioDTO mapToDTO(Orario orario) {
        return OrarioDTO.builder()
                .id(orario.getId())
                .palestraId(orario.getPalestra().getId())
                .palestraNome(orario.getPalestra().getNome())
                .gruppoId(orario.getGruppo().getId())
                .gruppoNome(orario.getGruppo().getNome())
                .numeroCampo(orario.getNumeroCampo())
                .giornoSettimana(orario.getGiornoSettimana())
                .oraInizio(orario.getOraInizio())
                .oraFine(orario.getOraFine())
                .capienzaMassima(orario.getCapienzaMassima())
                .postiDisponibili(orario.getPostiDisponibili())
                .attivo(orario.getAttivo())
                .createdAt(orario.getCreatedAt())
                .updatedAt(orario.getUpdatedAt())
                .build();
    }
}
