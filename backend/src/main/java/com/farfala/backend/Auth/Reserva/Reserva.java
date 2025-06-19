package com.farfala.backend.Auth.Reserva;

import com.farfala.backend.Clase.Clase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId; // o puedes relacionar con entidad Usuario

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    private String fecha;
    private String hora;

    public Reserva() {}

    public Reserva(Long usuarioId, Clase clase, String fecha, String hora) {
        this.usuarioId = usuarioId;
        this.clase = clase;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
}
