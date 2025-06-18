package com.farfala.backend.Clase;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioClaseService {

    private final HorarioClaseRepository horarioClaseRepository;

    public HorarioClaseService(HorarioClaseRepository horarioClaseRepository) {
        this.horarioClaseRepository = horarioClaseRepository;
    }

    public HorarioClase guardarHorario(HorarioClase horario) {
        return horarioClaseRepository.save(horario);
    }

    public List<HorarioClase> listarPorClase(Long claseId) {
        return horarioClaseRepository.findByClaseId(claseId);
    }

    public Optional<HorarioClase> obtenerPorId(Long id) {
        return horarioClaseRepository.findById(id);
    }

    public void eliminarHorario(Long id) {
        horarioClaseRepository.deleteById(id);
    }
}