package com.farfala.backend.Clase;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "horario_clase")
public class HorarioClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // 👈 Evita guardar horarios sin fecha
    private LocalDate fecha;

    @Column(nullable = false) // 👈 Evita guardar horarios sin hora
    private LocalTime hora;

    @Column(nullable = false) // 👈 Capacidad también es obligatoria
    private int capacidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    // 🔹 Constructor vacío requerido por JPA
    public HorarioClase() {}

    // 🔹 Constructor personalizado
    public HorarioClase(LocalDate fecha, LocalTime hora, int capacidad, Clase clase) {
        this.fecha = fecha;
        this.hora = hora;
        this.capacidad = capacidad;
        this.clase = clase;
    }

    // 🔹 Getters y Setters
    public Long getId() {
        return id;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "HorarioClase{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", capacidad=" + capacidad +
                '}';
    }
}
