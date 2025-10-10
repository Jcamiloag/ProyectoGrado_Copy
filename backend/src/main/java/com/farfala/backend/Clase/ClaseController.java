package com.farfala.backend.Clase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/api/clases")
@CrossOrigin(origins = "*") // Permite conexión con Flutter
public class ClaseController {

    private final ClaseService claseService;
    private final HorarioClaseRepository horarioClaseRepository;
    private final ClaseRepository claseRepository;

    @Autowired
    public ClaseController(
            ClaseService claseService,
            HorarioClaseRepository horarioClaseRepository,
            ClaseRepository claseRepository) {
        this.claseService = claseService;
        this.horarioClaseRepository = horarioClaseRepository;
        this.claseRepository = claseRepository;
    }

    // 🔹 Obtener todas las clases
    @GetMapping
    public ResponseEntity<List<Clase>> obtenerClases() {
        List<Clase> clases = claseService.listarClases();

        // Evita recursión y limpia horarios nulos
        clases.forEach(clase -> {
            if (clase.getHorarios() != null) {
                clase.setHorarios(
                        clase.getHorarios().stream()
                                .filter(h -> h.getHora() != null)
                                .peek(h -> h.setClase(null))
                                .toList());
            }
        });

        return ResponseEntity.ok(clases);
    }

    // 🔹 Obtener una clase por ID
    @GetMapping("/{id}")
    public ResponseEntity<Clase> obtenerClase(@PathVariable Long id) {
        Clase clase = claseService.obtenerClase(id);
        if (clase == null)
            return ResponseEntity.notFound().build();

        // Limpieza de horarios
        if (clase.getHorarios() != null) {
            clase.setHorarios(
                    clase.getHorarios().stream()
                            .filter(h -> h.getHora() != null)
                            .peek(h -> h.setClase(null))
                            .toList());
        }

        return ResponseEntity.ok(clase);
    }

    // 🔹 Crear nueva clase
    @PostMapping
    public ResponseEntity<Clase> crearClase(@RequestBody Clase clase) {
        if (clase.getNombre() == null || clase.getDescripcion() == null) {
            return ResponseEntity.badRequest().build();
        }
        clase.setActiva(true);
        Clase nueva = claseService.guardarClase(clase);
        return ResponseEntity.status(201).body(nueva);
    }

    // 🔹 Agregar horario a clase
    @PostMapping("/{id}/horarios")
    public ResponseEntity<?> agregarHorario(@PathVariable Long id, @RequestBody HorarioRequest request) {
        try {
            Clase clase = claseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

            System.out.println("📅 Recibido desde Flutter:");
            System.out.println("   Fecha: " + request.getFecha());
            System.out.println("   Hora: " + request.getHora());
            System.out.println("   Capacidad: " + request.getCapacidad());

            // Validación
            if (request.getFecha() == null || request.getHora() == null || request.getHora().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La hora no puede ser nula o vacía");
            }

            LocalDate fecha = LocalDate.parse(request.getFecha());
            LocalTime hora;

            try {
                hora = LocalTime.parse(request.getHora());
            } catch (Exception e) {
                System.out.println("⚠️ Error al convertir hora: " + e.getMessage());
                return ResponseEntity.badRequest().body("Formato de hora inválido. Usa HH:mm");
            }

            HorarioClase horario = new HorarioClase(fecha, hora, request.getCapacidad(), clase);
            HorarioClase guardado = horarioClaseRepository.save(horario);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("id", guardado.getId());
            respuesta.put("fecha", guardado.getFecha().toString());
            respuesta.put("hora", guardado.getHora().toString());
            respuesta.put("capacidad", guardado.getCapacidad());
            respuesta.put("claseId", clase.getId());

            return ResponseEntity.status(201).body(respuesta);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error interno al guardar horario: " + e.getMessage());
        }
    }

    // 🔹 Listar horarios válidos de una clase
    @GetMapping("/{id}/horarios")
    public ResponseEntity<List<Map<String, Object>>> obtenerHorarios(@PathVariable Long id) {
        List<HorarioClase> horarios = horarioClaseRepository.findByClaseId(id);

        // Filtrar y transformar
        List<Map<String, Object>> respuesta = horarios.stream()
                .filter(h -> h.getHora() != null)
                .map(h -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", h.getId());
                    map.put("fecha", h.getFecha().toString());
                    map.put("hora", h.getHora().toString());
                    map.put("capacidad", h.getCapacidad());
                    return map;
                })
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    // 🔹 Obtener horario principal
    @GetMapping("/{id}/horario")
    public ResponseEntity<Map<String, String>> obtenerHoraPrincipal(@PathVariable Long id) {
        HorarioClase horario = horarioClaseRepository.findFirstByClaseId(id);
        if (horario == null || horario.getHora() == null)
            return ResponseEntity.notFound().build();

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("hora", horario.getHora().toString());
        return ResponseEntity.ok(respuesta);
    }

    // 🔹 Actualizar clase
    @PutMapping("/{id}")
    public ResponseEntity<Clase> actualizarClase(@PathVariable Long id, @RequestBody Clase claseActualizada) {
        Clase actualizada = claseService.actualizarClase(id, claseActualizada);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    // 🔹 Eliminar clase
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClase(@PathVariable Long id) {
        Clase existente = claseService.obtenerClase(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        claseService.eliminarClase(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
