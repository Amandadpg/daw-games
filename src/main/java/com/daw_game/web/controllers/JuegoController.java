package com.daw_game.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw_game.persistence.entities.Juego;
import com.daw_game.services.JuegoServices;

@RestController
@RequestMapping("/juegos")
public class JuegoController {

	@Autowired
    private JuegoServices juegoServices;

    @GetMapping
    public List<Juego> getAll() {
        return juegoServices.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> getById(@PathVariable int id) {
        return juegoServices.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Juego create(@RequestBody Juego juego) {
        return juegoServices.crearJuego(juego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juego> update(@PathVariable int id, @RequestBody Juego juego) {
        return juegoServices.modificarJuego(id, juego)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        juegoServices.borrarJuego(id);
    }

    @PatchMapping("/{id}/completado")
    public ResponseEntity<Juego> marcarCompletado(@PathVariable int id, @RequestParam boolean completado) {
        return juegoServices.marcarCompletado(id, completado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/genero")
    public List<Juego> buscarPorGenero(@RequestParam String genero) {
        return juegoServices.buscarPorGenero(genero);
    }

    @GetMapping("/buscar/nombre")
    public List<Juego> buscarPorNombre(@RequestParam String nombre) {
        return juegoServices.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/plataforma")
    public List<Juego> buscarPorPlataforma(@RequestParam String plataforma) {
        return juegoServices.buscarPorPlataforma(plataforma);
    }

    @GetMapping("/expansiones")
    public List<Juego> obtenerExpansiones() {
        return juegoServices.obtenerExpansiones();
    }

    @GetMapping("/dlcs")
    public List<Juego> obtenerDLCs() {
        return juegoServices.obtenerDLCs();
    }

    @GetMapping("/base")
    public List<Juego> obtenerJuegosBase() {
        return juegoServices.obtenerJuegosBase();
    }

    @GetMapping("/precio")
    public List<Juego> buscarPorPrecio(@RequestParam double min, @RequestParam double max) {
        return juegoServices.buscarPorRangoPrecio(min, max);
    }

    @GetMapping("/populares")
    public List<Juego> juegosPopulares() {
        return juegoServices.juegosConMasDeMilDescargas();
    }
}



