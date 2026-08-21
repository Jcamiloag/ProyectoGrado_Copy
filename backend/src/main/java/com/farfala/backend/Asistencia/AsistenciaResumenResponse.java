package com.farfala.backend.Asistencia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AsistenciaResumenResponse {

    private Long horarioId;

    private String nombreClase;

    private String categoria;

    private String fecha;

    private String hora;

    private Integer inscritos;

}