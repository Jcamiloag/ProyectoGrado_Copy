package com.farfala.backend.Reserva;

import com.farfala.backend.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final JwtService jwtService;

    public ReservaController(
            ReservaService reservaService,
            JwtService jwtService
    ) {
        this.reservaService = reservaService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaRequest request, HttpServletRequest httpRequest) {
        String tokenHeader = httpRequest.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).body("Token no proporcionado o inválido");
        }

        String token = tokenHeader.substring(7);
        Long usuarioId = jwtService.getUserIdFromToken(token).longValue();

        Reserva reservaGuardada = reservaService.crearReserva(request, usuarioId);

        return ResponseEntity.ok(reservaGuardada);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Reserva>> obtenerReservasUsuario(HttpServletRequest httpRequest) {
        String tokenHeader = httpRequest.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).build();
        }

        String token = tokenHeader.substring(7);
        Long usuarioId = jwtService.getUserIdFromToken(token).longValue();

        List<Reserva> reservas = reservaService.listarReservasUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }
}
