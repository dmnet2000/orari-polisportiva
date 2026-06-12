package com.polisportiva.repository;

import com.polisportiva.entity.Palestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalestraRepository extends JpaRepository<Palestra, Long> {
    Optional<Palestra> findByNome(String nome);
    List<Palestra> findByAttiva(Boolean attiva);
    List<Palestra> findByCitta(String citta);
}
