package com.farfala.backend.Asistencia;


import lombok.*;


@Getter
@Setter
public class AsistenciaResponse {


    private Long id;

    private Long reservaId;

    private String nombreUsuario;

    private String nombreClase;

    private String categoria;

    private String hora;

    private String fecha;

    private String estadoReserva;

    private String estadoAsistencia;



    public AsistenciaResponse(Asistencia asistencia){


        this.id = asistencia.getId();


        this.reservaId =
                asistencia.getReserva().getId();



        this.nombreUsuario =
                asistencia.getReserva()
                        .getUser()
                        .getFirstname()
                + " "
                +
                asistencia.getReserva()
                        .getUser()
                        .getLastname();



        this.nombreClase =
                asistencia.getReserva()
                        .getHorarioClase()
                        .getClase()
                        .getNombre();



        this.categoria =
                asistencia.getReserva()
                        .getHorarioClase()
                        .getClase()
                        .getCategoria();



        this.hora =
                asistencia.getReserva()
                        .getHorarioClase()
                        .getHora();



        this.fecha =
                asistencia.getReserva()
                        .getHorarioClase()
                        .getFecha();



        this.estadoReserva =
                asistencia.getReserva()
                        .getEstado();



        this.estadoAsistencia =
                asistencia.getEstado();

    }

}