package com.farfala.backend.Clase;

public class HorarioRequest {
    private String fecha; // Se enviará como texto desde Flutter ("2025-10-07")
    private String hora; // También texto ("17:30")
    private int capacidad;

    public HorarioRequest() {
    }

    public HorarioRequest(String fecha, String hora, int capacidad) {
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
