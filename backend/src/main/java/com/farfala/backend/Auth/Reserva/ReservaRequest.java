package com.farfala.backend.Auth.Reserva;

public class ReservaRequest {
    private Long usuarioId;
    private Long claseId;
    private String fecha;
    private String hora;

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
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

  
}

