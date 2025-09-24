
package com.farfala.backend.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRequest {
    private Long usuarioId;
    private Long claseId;
    private Long horarioId;
    private LocalDate fecha;
    private LocalTime hora;
   

    public ReservaRequest() {
    }

      // Getters y Setters

    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}