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
        Clase clase = claseRepository.findById(id).orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        HorarioClase horario = new HorarioClase(request.getFecha(), request.getHora(), clase);
        horarioClaseRepository.save(horario);

        return ResponseEntity.ok("Horario agregado con éxito");
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
