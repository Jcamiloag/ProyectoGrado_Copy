package com.farfala.backend.Reserva;

import java.time.LocalDateTime;

import com.farfala.backend.Clase.HorarioClase;
import com.farfala.backend.Plan.Plan;
import com.farfala.backend.User.User;
import com.farfala.backend.Asistencia.Asistencia;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // Usuario que realizó la reserva
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    @ToString.Exclude
    private User user;



    // Plan del cual se descontó la clase
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "plan_id",
        nullable = false
    )
    @ToString.Exclude
    private Plan plan;



    // Horario reservado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "horario_clase_id",
        nullable = false
    )
    private HorarioClase horarioClase;



    // Fecha y hora en la que se realizó la reserva
    @Builder.Default
    private LocalDateTime fechaReserva = LocalDateTime.now();



    // ACTIVA, CANCELADA, ASISTIÓ, NO_ASISTIÓ
    @Builder.Default
    private String estado = "ACTIVA";

    @OneToOne(mappedBy = "reserva")
private Asistencia asistencia;


}