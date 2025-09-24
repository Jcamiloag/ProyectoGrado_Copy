package com.farfala.backend.Clase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;
    private final HorarioClaseRepository horarioClaseRepository;
    private final ClaseRepository claseRepository;

    @Autowired
    public ClaseController(ClaseService claseService,
            HorarioClaseRepository horarioClaseRepository,
            ClaseRepository claseRepository) {
        this.claseService = claseService;
        this.horarioClaseRepository = horarioClaseRepository;
        this.claseRepository = claseRepository;
    }

    @GetMapping
    public List<Clase> obtenerClases() {
        return claseService.listarClases();
    }

    @GetMapping("/{id}")
    public Clase obtenerClase(@PathVariable Long id) {
        return claseService.obtenerClase(id);
    }

    @PostMapping
    public Clase crearClase(@RequestBody Clase clase) {
        return claseService.guardarClase(clase);
    }

    @PutMapping("/{id}")
    public Clase actualizarClase(@PathVariable Long id, @RequestBody Clase claseActualizada) {
        return claseService.actualizarClase(id, claseActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarClase(@PathVariable Long id) {
        claseService.eliminarClase(id);
    }

    @PostMapping("/{id}/horarios")
public ResponseEntity<?> agregarHorario(@PathVariable Long id, @RequestBody HorarioRequest request) {
    try {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        if (request.getFecha() == null || request.getHora() == null ||
            request.getFecha().isBlank() || request.getHora().isBlank()) {
            return ResponseEntity.badRequest().body("La fecha y hora son obligatorias");
        }

        HorarioClase horario = new HorarioClase(request.getFecha(), request.getHora(), clase);
        HorarioClase guardado = horarioClaseRepository.save(horario);

        // Devolver 201 Created con el objeto guardado (id incluido)
        return ResponseEntity.status(201).body(guardado);
    } catch (Exception ex) {
        // Loguea el error en consola para poder ver stack trace en logs
        ex.printStackTrace();
        // Devuelve mensaje claro al frontend para debug
        return ResponseEntity.status(500).body("Error al guardar horario: " + ex.getMessage());
    }
}

    @GetMapping("/{id}/horarios")
    public ResponseEntity<List<HorarioClase>> obtenerHorarios(@PathVariable Long id) {
        List<HorarioClase> horarios = horarioClaseRepository.findByClaseId(id);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}/horario")
    public ResponseEntity<Map<String, String>> obtenerHoraPrincipal(@PathVariable Long id) {
        HorarioClase horario = horarioClaseRepository.findFirstByClaseId(id);
        if (horario == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("hora", horario.getHora());
        return ResponseEntity.ok(respuesta);
    }
}