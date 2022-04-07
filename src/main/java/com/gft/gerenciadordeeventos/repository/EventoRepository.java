package com.gft.gerenciadordeeventos.repository;

import com.gft.gerenciadordeeventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findByNomeAndDataEvento(String nome, LocalDate dataEvento);
    Optional<List<Evento>> findByNomeContains(String nome);
}
