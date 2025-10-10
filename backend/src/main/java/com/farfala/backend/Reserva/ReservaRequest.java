package com.farfala.backend.Reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRequest {
    private Long claseId;
    private Long horarioId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;

    public ReservaRequest() {}

    public Long getClaseId() { return claseId; }
    public void setClaseId(Long claseId) { this.claseId = claseId; }
    public Long getHorarioId() { return horarioId; }
    public void setHorarioId(Long horarioId) { this.horarioId = horarioId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
}
