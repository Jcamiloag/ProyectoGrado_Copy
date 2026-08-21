package com.farfala.backend.Clase;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Clase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String nombre;


    private String descripcion;


    private String categoria;


    private boolean activa;




    @OneToMany(
            mappedBy = "clase",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<HorarioClase> horarios;



    public Clase(){}



    public Clase(
            String nombre,
            String descripcion,
            String categoria,
            boolean activa
    ){

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.activa = activa;

    }



    public Long getId(){

        return id;

    }



    public String getNombre(){

        return nombre;

    }



    public void setNombre(String nombre){

        this.nombre = nombre;

    }



    public String getDescripcion(){

        return descripcion;

    }



    public void setDescripcion(String descripcion){

        this.descripcion = descripcion;

    }



    public String getCategoria(){

        return categoria;

    }



    public void setCategoria(String categoria){

        this.categoria = categoria;

    }



    public boolean isActiva(){

        return activa;

    }



    public void setActiva(boolean activa){

        this.activa = activa;

    }



    public List<HorarioClase> getHorarios(){

        return horarios;

    }



    public void setHorarios(List<HorarioClase> horarios){

        this.horarios = horarios;

    }

}