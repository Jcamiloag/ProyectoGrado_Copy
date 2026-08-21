package com.farfala.backend.Clase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioClaseRepository extends JpaRepository<HorarioClase, Long> {

    List<HorarioClase> findByClaseId(Long claseId);

    HorarioClase findFirstByClaseId(Long claseId);

    List<HorarioClase> findAllByOrderByFechaDescHoraAsc();

}