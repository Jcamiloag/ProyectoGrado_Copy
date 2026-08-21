package com.farfala.backend.Clase;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


@Entity
@Table(name = "horario_clase")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HorarioClase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String hora;



    private String fecha;



    private Integer cupos;



    private Integer cuposDisponibles;



    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    @JsonIgnore
    private Clase clase;




    public HorarioClase() {
    }




    public HorarioClase(
            String fecha,
            String hora,
            Integer cupos,
            Clase clase
    ){

        this.fecha = fecha;

        this.hora = hora;

        this.cupos = cupos;

        this.cuposDisponibles = cupos;

        this.clase = clase;

    }





    public Long getId() {
        return id;
    }




    public String getHora() {
        return hora;
    }



    public void setHora(String hora) {
        this.hora = hora;
    }




    public String getFecha() {
        return fecha;
    }



    public void setFecha(String fecha) {
        this.fecha = fecha;
    }




    public Integer getCupos() {
        return cupos;
    }



    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }




    public Integer getCuposDisponibles() {
        return cuposDisponibles;
    }



    public void setCuposDisponibles(Integer cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }




    public Clase getClase() {
        return clase;
    }



    public void setClase(Clase clase) {
        this.clase = clase;
    }


}