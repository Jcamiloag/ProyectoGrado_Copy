package com.farfala.backend.Clase;

import com.farfala.backend.Plan.PlanRepository;

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
    private final PlanRepository planRepository;

    @Autowired
    public ClaseController(
            ClaseService claseService,
            HorarioClaseRepository horarioClaseRepository,
            ClaseRepository claseRepository,
            PlanRepository planRepository
    ) {

        this.claseService = claseService;
        this.horarioClaseRepository = horarioClaseRepository;
        this.claseRepository = claseRepository;
        this.planRepository = planRepository;

    }

    @GetMapping
    public List<Clase> obtenerClases() {

        return claseService.listarClases();

    }

    @GetMapping("/{id}")
    public Clase obtenerClase(
            @PathVariable Long id
    ) {

        return claseService.obtenerClase(id);

    }

    @PostMapping
    public Clase crearClase(
            @RequestBody Clase clase
    ) {

        return claseService.guardarClase(clase);

    }

    @PutMapping("/{id}")
    public Clase actualizarClase(
            @PathVariable Long id,
            @RequestBody Clase claseActualizada
    ) {

        return claseService.actualizarClase(
                id,
                claseActualizada
        );

    }

    @DeleteMapping("/{id}")
    public void eliminarClase(
            @PathVariable Long id
    ) {

        claseService.eliminarClase(id);

    }

    // ==================================================
    // AGREGAR HORARIO A UNA CLASE
    // ==================================================

    @PostMapping("/{id}/horarios")
    public ResponseEntity<?> agregarHorario(
            @PathVariable Long id,
            @RequestBody HorarioRequest request
    ) {

        Clase clase =
                claseRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Clase no encontrada"
                                )
                        );

        HorarioClase horario =
                new HorarioClase(

                        request.getFecha(),

                        request.getHora(),

                        request.getCupos(),

                        clase

                );

        horarioClaseRepository.save(
                horario
        );

        return ResponseEntity.ok(
                "Horario agregado con éxito"
        );

    }

    // ==================================================
    // ACTUALIZAR HORARIO EXISTENTE
    // ==================================================

    @PutMapping("/horarios/{horarioId}")
    public ResponseEntity<?> actualizarHorario(
            @PathVariable Long horarioId,
            @RequestBody HorarioRequest request
    ) {

        HorarioClase horario =
                horarioClaseRepository.findById(horarioId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Horario no encontrado"
                                )
                        );

        horario.setHora(
                request.getHora()
        );

        horario.setFecha(
                request.getFecha()
        );

        if (request.getCupos() != null) {

            int cuposAnteriores = horario.getCupos();
            int disponiblesActuales = horario.getCuposDisponibles();

            int reservados = cuposAnteriores - disponiblesActuales;

            int nuevosCupos = request.getCupos();

            horario.setCupos(nuevosCupos);

            int nuevosDisponibles = nuevosCupos - reservados;

            if (nuevosDisponibles < 0) {
                nuevosDisponibles = 0;
            }

            horario.setCuposDisponibles(nuevosDisponibles);

        }

        horarioClaseRepository.save(
                horario
        );

        return ResponseEntity.ok(
                "Horario actualizado correctamente"
        );

    }

    // ==================================================
    // OBTENER HORARIOS DE UNA CLASE
    // ==================================================

    @GetMapping("/{id}/horarios")
    public ResponseEntity<List<HorarioClase>> obtenerHorarios(
            @PathVariable Long id
    ) {

        List<HorarioClase> horarios =
                horarioClaseRepository.findByClaseId(id);

        return ResponseEntity.ok(
                horarios
        );

    }

    // ==================================================
    // OBTENER HORA PRINCIPAL
    // ==================================================

    @GetMapping("/{id}/horario")
    public ResponseEntity<Map<String, String>> obtenerHoraPrincipal(
            @PathVariable Long id
    ) {

        HorarioClase horario =
                horarioClaseRepository
                        .findFirstByClaseId(id);

        if (horario == null) {

            return ResponseEntity
                    .notFound()
                    .build();

        }

        Map<String, String> respuesta =
                new HashMap<>();

        respuesta.put(
                "hora",
                horario.getHora()
        );

        return ResponseEntity.ok(
                respuesta
        );

    }

    // ==================================================
    // CLASES DISPONIBLES PARA UN USUARIO SEGÚN SUS PLANES
    // ==================================================

    @GetMapping("/usuario/{userId}/disponibles")
    public ResponseEntity<List<Clase>> clasesDisponiblesUsuario(
            @PathVariable Long userId
    ) {

        List<String> categorias =
                planRepository
                        .findCategoriasActivasPorUsuario(userId);

        if (categorias.isEmpty()) {

            return ResponseEntity.ok(
                    List.of()
            );

        }

        List<Clase> clases =
                claseRepository
                        .findByCategoriaIn(categorias);

        return ResponseEntity.ok(
                clases
        );

    }

}