package com.polisportiva.repository;

import com.polisportiva.entity.Gruppo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GruppoRepository extends JpaRepository<Gruppo, Long> {
    List<Gruppo> findByDisciplinaId(Long disciplinaId);
    List<Gruppo> findByAttivo(Boolean attivo);
}
