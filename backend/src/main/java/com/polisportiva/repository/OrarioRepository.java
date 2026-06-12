package com.polisportiva.repository;

import com.polisportiva.entity.Orario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrarioRepository extends JpaRepository<Orario, Long> {
    List<Orario> findByPalestraId(Long palestraId);
    List<Orario> findByGruppoId(Long gruppoId);
    List<Orario> findByGiornoSettimana(String giornoSettimana);
    List<Orario> findByAttivo(Boolean attivo);
}
