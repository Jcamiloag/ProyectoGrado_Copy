package com.farfala.backend.Auth.Reserva;
import com.farfala.backend.Auth.Reserva.ReservaRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farfala.backend.Clase.Clase;
import com.farfala.backend.Clase.ClaseRepository;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final ClaseRepository claseRepository;

    public ReservaController(ReservaRepository reservaRepository, ClaseRepository claseRepository) {
        this.reservaRepository = reservaRepository;
        this.claseRepository = claseRepository;
    }

   @PostMapping
public ResponseEntity<?> reservarClase(@RequestBody ReservaRequest request) {
    Clase clase = claseRepository.findById(request.getClaseId())
        .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

    Reserva reserva = new Reserva(
        request.getUsuarioId(),
        clase,
        request.getFecha(),
        request.getHora()
    );
    reservaRepository.save(reserva);

    return ResponseEntity.ok("Reserva guardada exitosamente");
    }
}

