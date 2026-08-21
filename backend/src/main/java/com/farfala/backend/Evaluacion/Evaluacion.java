package com.farfala.backend.Evaluacion;

import java.time.LocalDate;

import com.farfala.backend.User.User;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluacion")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer edad;

    private Double estatura;

    private Double peso;

    private Integer experienciaPole;

    private Integer experienciaDeportiva;

    private Integer flexibilidad;

    private Integer fuerza;

    private Boolean lesiones;

    private String descripcionLesion;

    private String objetivo;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private LocalDate fechaEvaluacion;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getExperienciaPole() {
        return experienciaPole;
    }

    public void setExperienciaPole(Integer experienciaPole) {
        this.experienciaPole = experienciaPole;
    }

    public Integer getExperienciaDeportiva() {
        return experienciaDeportiva;
    }

    public void setExperienciaDeportiva(Integer experienciaDeportiva) {
        this.experienciaDeportiva = experienciaDeportiva;
    }

    public Integer getFlexibilidad() {
        return flexibilidad;
    }

    public void setFlexibilidad(Integer flexibilidad) {
        this.flexibilidad = flexibilidad;
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        this.fuerza = fuerza;
    }

    public Boolean getLesiones() {
        return lesiones;
    }

    public void setLesiones(Boolean lesiones) {
        this.lesiones = lesiones;
    }

    public String getDescripcionLesion() {
        return descripcionLesion;
    }

    public void setDescripcionLesion(String descripcionLesion) {
        this.descripcionLesion = descripcionLesion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
}