package com.farfala.backend.Reserva;

import com.farfala.backend.Clase.Clase;
import com.farfala.backend.Clase.ClaseRepository;
import com.farfala.backend.Clase.HorarioClase;
import com.farfala.backend.Clase.HorarioClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClaseRepository claseRepository;
    private final HorarioClaseRepository horarioClaseRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            ClaseRepository claseRepository,
            HorarioClaseRepository horarioClaseRepository
    ) {
        this.reservaRepository = reservaRepository;
        this.claseRepository = claseRepository;
        this.horarioClaseRepository = horarioClaseRepository;
    }

    // Crear reserva desde el request
    public Reserva crearReserva(ReservaRequest request, Long usuarioId) {
        Clase clase = claseRepository.findById(request.getClaseId())
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        HorarioClase horario = horarioClaseRepository.findById(request.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        Reserva reserva = new Reserva(
                usuarioId,
                clase,
                request.getFecha(),
                request.getHora(),
                horario
        );

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservasUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Reserva> obtenerReserva(Long id) {
        return reservaRepository.findById(id);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
