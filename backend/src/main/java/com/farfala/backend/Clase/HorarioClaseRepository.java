package com.farfala.backend.Clase;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HorarioClaseRepository extends JpaRepository<HorarioClase, Long> {
    List<HorarioClase> findByClaseId(Long claseId);
    HorarioClase findFirstByClaseId(Long claseId); 
}
