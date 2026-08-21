package com.farfala.backend.Reserva;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReservaController {


    private final ReservaService service;



    // =====================================
    // CREAR RESERVA
    // =====================================

    @PostMapping
    public ResponseEntity<?> crear(
            @RequestBody ReservaRequest request
    ) {

        try {


            Reserva reserva = service.crearReserva(
                    request.getUserId(),
                    request.getHorarioId()
            );


            return ResponseEntity.ok(
                    new ReservaResponse(reserva)
            );


        } catch (Exception e) {


            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());


        }

    }




    // =====================================
    // RESERVAS DE UN USUARIO
    // =====================================

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> listarUsuario(
            @PathVariable Long userId
    ) {


        List<ReservaResponse> respuesta = service
                .listarUsuario(userId)
                .stream()
                .map(ReservaResponse::new)
                .toList();


        return ResponseEntity.ok(respuesta);

    }




    // =====================================
    // CANCELAR RESERVA
    // =====================================

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(
            @PathVariable Long id
    ) {


        try {


            Reserva reserva = service.cancelarReserva(id);


            return ResponseEntity.ok(
                    new ReservaResponse(reserva)
            );


        } catch (Exception e) {


            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());


        }

    }
    // =====================================
// RESERVAS ACTIVAS DE UN HORARIO
// =====================================

@GetMapping("/horario/{horarioId}")
public ResponseEntity<?> listarReservasHorario(
        @PathVariable Long horarioId
) {

    List<ReservaResponse> respuesta = service
            .listarReservasHorario(horarioId)
            .stream()
            .map(ReservaResponse::new)
            .toList();


    return ResponseEntity.ok(respuesta);

}


}