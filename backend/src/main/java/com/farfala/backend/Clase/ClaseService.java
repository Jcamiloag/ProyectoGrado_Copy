package com.farfala.backend.Clase;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;

    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public List<Clase> listarClases() {
        return claseRepository.findAll();
    }

    public Clase obtenerClase(Long id) {
        return claseRepository.findById(id).orElse(null);
    }

    public Clase guardarClase(Clase clase) {
        if (clase.getHorarios() != null) {
            clase.getHorarios().forEach(h -> h.setClase(clase));
        }
        return claseRepository.save(clase);
    }

    public Clase actualizarClase(Long id, Clase nuevaClase) {
        Clase existente = claseRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }

        if (nuevaClase.getNombre() != null)
            existente.setNombre(nuevaClase.getNombre());
        if (nuevaClase.getDescripcion() != null)
            existente.setDescripcion(nuevaClase.getDescripcion());
        if (nuevaClase.getCategoria() != null)
            existente.setCategoria(nuevaClase.getCategoria());
        existente.setActiva(nuevaClase.isActiva());

        // Actualizar horarios si vienen
        if (nuevaClase.getHorarios() != null && !nuevaClase.getHorarios().isEmpty()) {
            existente.getHorarios().clear();
            nuevaClase.getHorarios().forEach(h -> h.setClase(existente));
            existente.getHorarios().addAll(nuevaClase.getHorarios());
        }

        return claseRepository.save(existente);
    }

    public void eliminarClase(Long id) {
        claseRepository.deleteById(id);
    }
}