package com.farfala.backend.Clase;


public class HorarioRequest {


    private String fecha;

    private String hora;

    private Integer cupos;



    public HorarioRequest() {
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



    public Integer getCupos() {
        return cupos;
    }



    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

}