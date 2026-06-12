package com.polisportiva.service;

import com.polisportiva.dto.PrenotazioneDTO;
import com.polisportiva.entity.Orario;
import com.polisportiva.entity.Prenotazione;
import com.polisportiva.entity.User;
import com.polisportiva.exception.ApiException;
import com.polisportiva.exception.ResourceNotFoundException;
import com.polisportiva.repository.OrarioRepository;
import com.polisportiva.repository.PrenotazioneRepository;
import com.polisportiva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private OrarioRepository orarioRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PrenotazioneDTO> getAllPrenotazioni() {
        return prenotazioneRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PrenotazioneDTO> getPrenotazioniByOrario(Long orarioId) {
        return prenotazioneRepository.findByOrarioId(orarioId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PrenotazioneDTO> getPrenotazioniByUtente(Long utenteId) {
        return prenotazioneRepository.findByUtenteId(utenteId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PrenotazioneDTO getPrenotazioneById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found with id: " + id));
        return mapToDTO(prenotazione);
    }

    public PrenotazioneDTO createPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        Orario orario = orarioRepository.findById(prenotazioneDTO.getOrarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Orario not found with id: " + prenotazioneDTO.getOrarioId()));

        User utente = userRepository.findById(prenotazioneDTO.getUtenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Utente not found with id: " + prenotazioneDTO.getUtenteId()));

        if (orario.getPostiDisponibili() < prenotazioneDTO.getNumeroPosti()) {
            throw new ApiException("Not enough available spots for booking");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setOrario(orario);
        prenotazione.setUtente(utente);
        prenotazione.setNumeroPosti(prenotazioneDTO.getNumeroPosti());
        prenotazione.setNote(prenotazioneDTO.getNote());
        prenotazione.setStato(Prenotazione.StatoPrenotazione.CONFERMATA);

        // Update available spots
        orario.setPostiDisponibili(orario.getPostiDisponibili() - prenotazioneDTO.getNumeroPosti());
        orarioRepository.save(orario);

        Prenotazione savedPrenotazione = prenotazioneRepository.save(prenotazione);
        return mapToDTO(savedPrenotazione);
    }

    public PrenotazioneDTO updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found with id: " + id));

        prenotazione.setStato(Prenotazione.StatoPrenotazione.valueOf(prenotazioneDTO.getStato()));
        prenotazione.setNote(prenotazioneDTO.getNote());

        Prenotazione updatedPrenotazione = prenotazioneRepository.save(prenotazione);
        return mapToDTO(updatedPrenotazione);
    }

    public void cancelPrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found with id: " + id));

        prenotazione.setStato(Prenotazione.StatoPrenotazione.ANNULLATA);

        // Restore available spots
        Orario orario = prenotazione.getOrario();
        orario.setPostiDisponibili(orario.getPostiDisponibili() + prenotazione.getNumeroPosti());
        orarioRepository.save(orario);

        prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione not found with id: " + id));
        prenotazioneRepository.delete(prenotazione);
    }

    private PrenotazioneDTO mapToDTO(Prenotazione prenotazione) {
        return PrenotazioneDTO.builder()
                .id(prenotazione.getId())
                .orarioId(prenotazione.getOrario().getId())
                .orarioDescrizione(prenotazione.getOrario().getGruppo().getNome() + " - " + prenotazione.getOrario().getGiornoSettimana())
                .utenteId(prenotazione.getUtente().getId())
                .utenteUsername(prenotazione.getUtente().getUsername())
                .stato(prenotazione.getStato().name())
                .numeroPosti(prenotazione.getNumeroPosti())
                .note(prenotazione.getNote())
                .createdAt(prenotazione.getCreatedAt())
                .updatedAt(prenotazione.getUpdatedAt())
                .build();
    }
}
