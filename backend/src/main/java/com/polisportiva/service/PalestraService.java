package com.polisportiva.service;

import com.polisportiva.dto.PalestraDTO;
import com.polisportiva.entity.Palestra;
import com.polisportiva.exception.ResourceNotFoundException;
import com.polisportiva.repository.PalestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PalestraService {

    @Autowired
    private PalestraRepository palestraRepository;

    public List<PalestraDTO> getAllPalestre() {
        return palestraRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PalestraDTO> getPalestreAttive() {
        return palestraRepository.findByAttiva(true).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PalestraDTO getPalestraById(Long id) {
        Palestra palestra = palestraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Palestra not found with id: " + id));
        return mapToDTO(palestra);
    }

    public PalestraDTO createPalestra(PalestraDTO palestraDTO) {
        Palestra palestra = new Palestra();
        palestra.setNome(palestraDTO.getNome());
        palestra.setDescrizione(palestraDTO.getDescrizione());
        palestra.setIndirizzo(palestraDTO.getIndirizzo());
        palestra.setCitta(palestraDTO.getCitta());
        palestra.setCap(palestraDTO.getCap());
        palestra.setTelefono(palestraDTO.getTelefono());
        palestra.setEmail(palestraDTO.getEmail());
        palestra.setNumCampi(palestraDTO.getNumCampi() != null ? palestraDTO.getNumCampi() : 1);
        palestra.setAttiva(true);

        Palestra savedPalestra = palestraRepository.save(palestra);
        return mapToDTO(savedPalestra);
    }

    public PalestraDTO updatePalestra(Long id, PalestraDTO palestraDTO) {
        Palestra palestra = palestraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Palestra not found with id: " + id));

        palestra.setNome(palestraDTO.getNome());
        palestra.setDescrizione(palestraDTO.getDescrizione());
        palestra.setIndirizzo(palestraDTO.getIndirizzo());
        palestra.setCitta(palestraDTO.getCitta());
        palestra.setCap(palestraDTO.getCap());
        palestra.setTelefono(palestraDTO.getTelefono());
        palestra.setEmail(palestraDTO.getEmail());
        palestra.setNumCampi(palestraDTO.getNumCampi());
        palestra.setAttiva(palestraDTO.getAttiva());

        Palestra updatedPalestra = palestraRepository.save(palestra);
        return mapToDTO(updatedPalestra);
    }

    public void deletePalestra(Long id) {
        Palestra palestra = palestraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Palestra not found with id: " + id));
        palestraRepository.delete(palestra);
    }

    private PalestraDTO mapToDTO(Palestra palestra) {
        return PalestraDTO.builder()
                .id(palestra.getId())
                .nome(palestra.getNome())
                .descrizione(palestra.getDescrizione())
                .indirizzo(palestra.getIndirizzo())
                .citta(palestra.getCitta())
                .cap(palestra.getCap())
                .telefono(palestra.getTelefono())
                .email(palestra.getEmail())
                .numCampi(palestra.getNumCampi())
                .attiva(palestra.getAttiva())
                .createdAt(palestra.getCreatedAt())
                .updatedAt(palestra.getUpdatedAt())
                .build();
    }
}
