package com.daw_game.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw_game.persistence.entities.Juego;
import com.daw_game.persistence.entities.enums.Tipo;

public interface JuegoRepository extends JpaRepository<Juego, Integer> {
    List<Juego> findByGeneroContainingIgnoreCase(String genero);
    List<Juego> findByNombreContainingIgnoreCase(String nombre);
    List<Juego> findByPlataformasContainingIgnoreCase(String plataforma);
    List<Juego> findByTipo(Tipo tipo);
    List<Juego> findByPrecioBetween(double min, double max);
    List<Juego> findByDescargasGreaterThan(long cantidad);
}


