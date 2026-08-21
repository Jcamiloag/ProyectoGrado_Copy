package com.farfala.backend.Reserva;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farfala.backend.Clase.HorarioClase;
import com.farfala.backend.User.User;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // =====================================
    // TODAS LAS RESERVAS DEL USUARIO
    // =====================================

    List<Reserva> findByUser(User user);



    // =====================================
    // RESERVAS ACTIVAS DEL USUARIO
    // =====================================

    List<Reserva> findByUserAndEstado(
            User user,
            String estado
    );



    // =====================================
    // VALIDAR SI YA TIENE RESERVA ACTIVA
    // =====================================

    boolean existsByUserAndHorarioClaseAndEstado(
            User user,
            HorarioClase horarioClase,
            String estado
    );



    // =====================================
    // RESERVAS ACTIVAS DE UN HORARIO
    // =====================================

    List<Reserva> findByHorarioClaseIdAndEstado(
            Long horarioId,
            String estado
    );

}