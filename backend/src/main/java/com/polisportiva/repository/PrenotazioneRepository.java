package com.polisportiva.repository;

import com.polisportiva.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByOrarioId(Long orarioId);
    List<Prenotazione> findByUtenteId(Long utenteId);
    List<Prenotazione> findByStato(String stato);
}
