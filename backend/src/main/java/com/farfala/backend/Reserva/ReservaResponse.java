package com.farfala.backend.Reserva;

import java.time.LocalDateTime;

public class ReservaResponse {

    private Long id;

    private Long userId;

    private String nombreUsuario;

    private Long planId;

    private Long horarioId;

    private String hora;

    private String fecha;

    private LocalDateTime fechaReserva;

    private String estado;

    private String nombreClase;

    private String categoria;


    public ReservaResponse(Reserva reserva) {

        this.id = reserva.getId();

        this.userId = reserva.getUser().getId();

        this.nombreUsuario =
                reserva.getUser().getFirstname()
                        + " "
                        + reserva.getUser().getLastname();


        this.planId = reserva.getPlan().getId();

        this.horarioId = reserva.getHorarioClase().getId();

        this.hora = reserva.getHorarioClase().getHora();

        this.fecha = reserva.getHorarioClase().getFecha();


        this.nombreClase =
                reserva.getHorarioClase()
                        .getClase()
                        .getNombre();


        this.categoria =
                reserva.getHorarioClase()
                        .getClase()
                        .getCategoria();


        this.fechaReserva = reserva.getFechaReserva();

        this.estado = reserva.getEstado();
    }


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Long getPlanId() {
        return planId;
    }

    public Long getHorarioId() {
        return horarioId;
    }

    public String getHora() {
        return hora;
    }

    public String getFecha() {
        return fecha;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public String getEstado() {
        return estado;
    }


    public String getNombreClase() {
        return nombreClase;
    }


    public String getCategoria() {
        return categoria;
    }
}