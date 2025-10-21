package com.farfala.backend.Reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Permite recibir hora en formato "HH:mm" o "HH:mm:ss" sin errores.
 */
@JsonDeserialize(using = ReservaRequestDeserializer.class)
public class ReservaRequest {

    private Long claseId;
    private Long horarioId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    // Eliminamos el formato estricto aquí, ya se maneja en el deserializador
    private LocalTime hora;

    public ReservaRequest() {
    }

    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public Long getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Long horarioId) {
        this.horarioId = horarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
