package com.farfala.backend.Evaluacion;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EvaluacionService {

    private final EvaluacionRepository repository;

    public EvaluacionService(EvaluacionRepository repository) {
        this.repository = repository;
    }

    public Evaluacion guardar(Evaluacion evaluacion) {
        return repository.save(evaluacion);
    }

    public List<Evaluacion> listar() {
        return repository.findAll();
    }

    public List<Evaluacion> buscarPorUsuario(Integer userId) {
        return repository.findByUserId(userId);
    }

    public Evaluacion actualizar(Integer id, Evaluacion evaluacionNueva) {

        Evaluacion evaluacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));

        evaluacion.setEdad(evaluacionNueva.getEdad());
        evaluacion.setEstatura(evaluacionNueva.getEstatura());
        evaluacion.setPeso(evaluacionNueva.getPeso());
        evaluacion.setExperienciaPole(evaluacionNueva.getExperienciaPole());
        evaluacion.setExperienciaDeportiva(evaluacionNueva.getExperienciaDeportiva());
        evaluacion.setFlexibilidad(evaluacionNueva.getFlexibilidad());
        evaluacion.setFuerza(evaluacionNueva.getFuerza());
        evaluacion.setLesiones(evaluacionNueva.getLesiones());
        evaluacion.setDescripcionLesion(evaluacionNueva.getDescripcionLesion());
        evaluacion.setObjetivo(evaluacionNueva.getObjetivo());
        evaluacion.setObservaciones(evaluacionNueva.getObservaciones());
        evaluacion.setFechaEvaluacion(evaluacionNueva.getFechaEvaluacion());

        return repository.save(evaluacion);
    }

}