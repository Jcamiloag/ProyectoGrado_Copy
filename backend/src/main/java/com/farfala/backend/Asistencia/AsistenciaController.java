package com.farfala.backend.Asistencia;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/asistencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AsistenciaController {


    private final AsistenciaService service;



    // =====================================
    // LISTAR ASISTENCIAS POR HORARIO
    // =====================================

    @GetMapping("/horario/{horarioId}")
    public ResponseEntity<?> listarPorHorario(
            @PathVariable Long horarioId
    ) {


        List<AsistenciaResponse> respuesta =
                service.listarPorHorario(horarioId)
                .stream()
                .map(AsistenciaResponse::new)
                .toList();


        return ResponseEntity.ok(respuesta);

    }





    // =====================================
    // ACTUALIZAR ESTADO DE ASISTENCIA
    // =====================================

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable Long id,
            @RequestBody EstadoAsistenciaRequest request
    ) {


        try {


            Asistencia asistencia =
                    service.actualizarEstado(
                            id,
                            request.getEstado()
                    );


            return ResponseEntity.ok(
                    new AsistenciaResponse(asistencia)
            );


        } catch(Exception e){


            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());


        }

    }


}