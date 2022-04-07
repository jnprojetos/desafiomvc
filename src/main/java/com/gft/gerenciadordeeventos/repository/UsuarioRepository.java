package com.gft.gerenciadordeeventos.repository;

import com.gft.gerenciadordeeventos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);
    Optional<List<Usuario>> findByNomeContains(String nome);
}
