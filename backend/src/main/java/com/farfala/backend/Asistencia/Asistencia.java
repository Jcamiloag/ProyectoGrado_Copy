package com.farfala.backend.Asistencia;

import com.farfala.backend.Reserva.Reserva;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asistencias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(
        name = "reserva_id",
        nullable = false
    )
    private Reserva reserva;


    // PENDIENTE, ASISTIO, NO_ASISTIO
    @Builder.Default
    private String estado = "PENDIENTE";

}