package com.orbitsound.repository;

import com.orbitsound.model.Artista;
import com.orbitsound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicasDoArtista m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscarMusicasPorArtista(String nome);
}
