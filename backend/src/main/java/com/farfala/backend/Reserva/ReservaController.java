
package com.farfala.backend.Reserva;

import com.farfala.backend.Clase.Clase;
import com.farfala.backend.Clase.ClaseRepository;
import com.farfala.backend.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final ClaseRepository claseRepository;
    private final JwtService jwtService;

    public ReservaController(ReservaRepository reservaRepository, ClaseRepository claseRepository, JwtService jwtService) {
        this.reservaRepository = reservaRepository;
        this.claseRepository = claseRepository;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaRequest request, HttpServletRequest httpRequest) {
        String tokenHeader = httpRequest.getHeader("Authorization");
        System.out.println("🛡️ Token recibido en backend: " + tokenHeader);

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).body("Token no proporcionado o inválido");
        }

        String token = tokenHeader.substring(7);
        Integer usuarioId = jwtService.getUserIdFromToken(token);

        Clase clase = claseRepository.findById(request.getClaseId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        // ✅ Aquí se usa el nuevo constructor de Reserva
        Reserva reserva = new Reserva(
                usuarioId,
                clase,
                request.getFecha(),
                request.getHora()
        );

        reservaRepository.save(reserva);

        return ResponseEntity.ok("Reserva guardada exitosamente");
    }
}