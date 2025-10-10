package com.farfala.backend.Clase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HorarioClaseRepository extends JpaRepository<HorarioClase, Long> {

    // Buscar horarios de una clase específica
    List<HorarioClase> findByClaseId(Long claseId);

    // Buscar horarios por fecha
    List<HorarioClase> findByFecha(LocalDate fecha);
    
    HorarioClase findFirstByClaseId(Long claseId);

    // Buscar horarios por clase y fecha
    List<HorarioClase> findByClaseIdAndFecha(Long claseId, LocalDate fecha);
}
