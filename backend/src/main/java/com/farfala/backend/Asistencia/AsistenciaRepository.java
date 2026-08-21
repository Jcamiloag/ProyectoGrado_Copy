package com.farfala.backend.Asistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AsistenciaRepository
        extends JpaRepository<Asistencia, Long> {


    List<Asistencia> findByReservaHorarioClaseId(Long horarioId);


    @Query("""
        SELECT a
        FROM Asistencia a
        ORDER BY
            a.reserva.horarioClase.fecha DESC,
            a.reserva.horarioClase.hora ASC
    """)
    List<Asistencia> findAllOrderByHorario();

}