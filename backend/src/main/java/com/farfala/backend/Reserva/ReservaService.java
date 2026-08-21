package com.farfala.backend.Reserva;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farfala.backend.Asistencia.Asistencia;
import com.farfala.backend.Asistencia.AsistenciaRepository;
import com.farfala.backend.Clase.HorarioClase;
import com.farfala.backend.Clase.HorarioClaseRepository;
import com.farfala.backend.Plan.Plan;
import com.farfala.backend.Plan.PlanRepository;
import com.farfala.backend.User.User;
import com.farfala.backend.User.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReservaService {


    private final ReservaRepository reservaRepository;
    private final UserRepository userRepository;
    private final HorarioClaseRepository horarioRepository;
    private final PlanRepository planRepository;
    private final AsistenciaRepository asistenciaRepository;



    // =====================================
    // CREAR RESERVA
    // =====================================

    @Transactional
    public Reserva crearReserva(
            Long userId,
            Long horarioId
    ) {


        User usuario = userRepository.findById(userId)
                .orElseThrow(
                    () -> new RuntimeException("Usuario no encontrado")
                );


        HorarioClase horario = horarioRepository.findById(horarioId)
                .orElseThrow(
                    () -> new RuntimeException("Horario no encontrado")
                );



        boolean existe = reservaRepository
                .existsByUserAndHorarioClaseAndEstado(
                        usuario,
                        horario,
                        "ACTIVA"
                );


        if (existe) {
            throw new RuntimeException(
                    "Ya tienes reservada esta clase"
            );
        }



        if (horario.getCuposDisponibles() == null ||
                horario.getCuposDisponibles() <= 0) {

            throw new RuntimeException(
                    "No hay cupos disponibles para este horario"
            );
        }



        String categoriaClase =
                horario.getClase().getCategoria();



        Plan plan =
                planRepository.findPlanActivoPorUsuarioYCategoria(
                        usuario.getId(),
                        categoriaClase
                );



        if (plan == null) {
            throw new RuntimeException(
                    "No tienes un plan disponible para esta clase"
            );
        }



        if (plan.getClasesRestantes() <= 0) {
            throw new RuntimeException(
                    "No tienes clases disponibles"
            );
        }



        // Descontar clase del plan
        plan.setClasesRestantes(
                plan.getClasesRestantes() - 1
        );

        planRepository.save(plan);



        // Descontar cupo
        horario.setCuposDisponibles(
                horario.getCuposDisponibles() - 1
        );

        horarioRepository.save(horario);



        Reserva reserva = Reserva.builder()
                .user(usuario)
                .plan(plan)
                .horarioClase(horario)
                .estado("ACTIVA")
                .build();



        Reserva reservaGuardada =
                reservaRepository.save(reserva);



        // =====================================
        // CREAR ASISTENCIA AUTOMÁTICA
        // =====================================

        Asistencia asistencia = new Asistencia();

        asistencia.setReserva(reservaGuardada);

        asistencia.setEstado("PENDIENTE");


        asistenciaRepository.save(asistencia);



        return reservaGuardada;

    }





    // =====================================
    // LISTAR RESERVAS DEL USUARIO
    // =====================================

    public List<Reserva> listarUsuario(Long userId) {


        User usuario = userRepository.findById(userId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Usuario no encontrado"
                    )
                );


        return reservaRepository.findByUser(usuario);

    }





    // =====================================
    // CANCELAR RESERVA
    // =====================================

    @Transactional
    public Reserva cancelarReserva(Long reservaId) {


        Reserva reserva =
                reservaRepository.findById(reservaId)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Reserva no encontrada"
                    )
                );



        if ("ACTIVA".equals(reserva.getEstado())) {


            Plan plan = reserva.getPlan();


            plan.setClasesRestantes(
                    plan.getClasesRestantes() + 1
            );


            planRepository.save(plan);



            HorarioClase horario =
                    reserva.getHorarioClase();


            horario.setCuposDisponibles(
                    horario.getCuposDisponibles() + 1
            );


            horarioRepository.save(horario);



            reserva.setEstado("CANCELADA");



            if (reserva.getAsistencia() != null) {

                reserva.getAsistencia()
                        .setEstado("CANCELADA");


                asistenciaRepository.save(
                        reserva.getAsistencia()
                );

            }

        }


        return reservaRepository.save(reserva);

    }





    // =====================================
    // LISTAR RESERVAS ACTIVAS DE UN HORARIO
    // =====================================

    public List<Reserva> listarReservasHorario(Long horarioId) {


        return reservaRepository
                .findByHorarioClaseIdAndEstado(
                        horarioId,
                        "ACTIVA"
                );

    }

}