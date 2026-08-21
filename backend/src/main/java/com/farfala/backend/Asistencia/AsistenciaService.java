package com.farfala.backend.Asistencia;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AsistenciaService {


    private final AsistenciaRepository repository;


    public List<Asistencia> listarPorHorario(Long horarioId){

        return repository.findByReservaHorarioClaseId(horarioId);

    }



    @Transactional
    public Asistencia actualizarEstado(
            Long id,
            String estado
    ){

        Asistencia asistencia =
                repository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Asistencia no encontrada"
                    )
                );


        asistencia.setEstado(estado);


        return repository.save(asistencia);

    }

}