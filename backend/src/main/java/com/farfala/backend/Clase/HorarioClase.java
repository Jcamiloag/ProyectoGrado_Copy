package com.farfala.backend.Clase;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class HorarioClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hora;
    private String fecha;

    private int capacidad = 10; // ✅ valor por defecto


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase_id")
    @JsonIgnore // no serializamos la clase cuando devolvemos el horario
    private Clase clase;

    public HorarioClase() {
    }

    public HorarioClase(String fecha, String hora, Clase clase) {
        this.fecha = fecha;
        this.hora = hora;
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

    public int getCapacidad(){
        return capacidad;
    }

    public void setCapacidad(){
        this.capacidad = capacidad;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
}
