package com.daw_game.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_game.persistence.entities.Juego;
import com.daw_game.persistence.entities.enums.Tipo;
import com.daw_game.persistence.repositories.JuegoRepository;

@Service
public class JuegoServices {

	@Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> obtenerTodos() {
        return juegoRepository.findAll();
    }

    public Optional<Juego> obtenerPorId(int id) {
        return juegoRepository.findById(id);
    }

    public Juego crearJuego(Juego juego) {
        if (juego.getFechaLanzamiento() == null) {
            juego.setFechaLanzamiento(LocalDate.now());
        }
        juego.setCompletado(false); // No se permite establecer al crear
        return juegoRepository.save(juego);
    }

    public Optional<Juego> modificarJuego(int id, Juego nuevoJuego) {
        return juegoRepository.findById(id).map(juegoExistente -> {
            juegoExistente.setNombre(nuevoJuego.getNombre());
            juegoExistente.setGenero(nuevoJuego.getGenero());
            juegoExistente.setPlataformas(nuevoJuego.getPlataformas());
            juegoExistente.setPrecio(nuevoJuego.getPrecio());
            juegoExistente.setDescargas(nuevoJuego.getDescargas());
            juegoExistente.setFechaLanzamiento(nuevoJuego.getFechaLanzamiento());
            juegoExistente.setTipo(nuevoJuego.getTipo());
           
            return juegoRepository.save(juegoExistente);
        });
    }

    public void borrarJuego(int id) {
        juegoRepository.deleteById(id);
    }

    public Optional<Juego> marcarCompletado(int id, boolean completado) {
        return juegoRepository.findById(id).map(juego -> {
            juego.setCompletado(completado);
            return juegoRepository.save(juego);
        });
    }

    public List<Juego> buscarPorGenero(String genero) {
        return juegoRepository.findByGeneroContainingIgnoreCase(genero);
    }

    public List<Juego> buscarPorNombre(String nombre) {
        return juegoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Juego> buscarPorPlataforma(String plataforma) {
        return juegoRepository.findByPlataformasContainingIgnoreCase(plataforma);
    }

    public List<Juego> obtenerExpansiones() {
        return juegoRepository.findByTipo(Tipo.EXPANSION);
    }

    public List<Juego> obtenerDLCs() {
        return juegoRepository.findByTipo(Tipo.DLC);
    }

    public List<Juego> obtenerJuegosBase() {
        return juegoRepository.findByTipo(Tipo.BASE);
    }

    public List<Juego> buscarPorRangoPrecio(double min, double max) {
        return juegoRepository.findByPrecioBetween(min, max);
    }

    public List<Juego> juegosConMasDeMilDescargas() {
        return juegoRepository.findByDescargasGreaterThan(1000L);
    }
}


