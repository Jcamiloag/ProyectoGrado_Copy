package com.farfala.backend.Reserva;

import com.farfala.backend.Clase.Clase;
import com.farfala.backend.Clase.ClaseRepository;
import com.farfala.backend.Clase.HorarioClase;
import com.farfala.backend.Clase.HorarioClaseRepository;
import com.farfala.backend.Jwt.JwtService;
import com.farfala.backend.User.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ClaseRepository claseRepository;
    private final HorarioClaseRepository horarioClaseRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public ReservaController(ReservaService reservaService,
                             ClaseRepository claseRepository,
<<<<<<< HEAD
=======
                             HorarioClaseRepository horarioClaseRepository,
                              UserRepository userRepository,
>>>>>>> feature/reservas
                             JwtService jwtService) {
        this.reservaService = reservaService;
        this.claseRepository = claseRepository;
        this.horarioClaseRepository = horarioClaseRepository;
         this.userRepository = userRepository;
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

        Clase clase = claseRepository.findById(request.getClaseId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

<<<<<<< HEAD
        Reserva reserva = new Reserva(usuarioId, clase, request.getFecha(), request.getHora());
=======
        HorarioClase horario = horarioClaseRepository.findById(request.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));;

         // ✅ ahora usamos LocalDate y LocalTime
        Reserva reserva = new Reserva(
                usuarioId,
                clase,
                request.getFecha(),
                request.getHora(),
                horario
        );
>>>>>>> feature/reservas

        reservaService.guardarReserva(reserva);

        return ResponseEntity.ok("Reserva guardada exitosamente");
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Reserva>> obtenerReservasUsuario(HttpServletRequest httpRequest) {
        String tokenHeader = httpRequest.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).build();
        }

        String token = tokenHeader.substring(7);
        Integer usuarioId = jwtService.getUserIdFromToken(token);

        List<Reserva> reservas = reservaService.listarReservasUsuario(Long.valueOf(usuarioId));
        return ResponseEntity.ok(reservas);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> feature/reservas
