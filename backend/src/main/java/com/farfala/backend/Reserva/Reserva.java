package com.farfala.backend.Reserva;

import com.farfala.backend.Clase.Clase;
import com.farfala.backend.Clase.HorarioClase;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioClase horarioClase;

    public Reserva(Long usuarioId, Clase clase, LocalDate fecha, LocalTime hora, HorarioClase horarioClase) {
        this.usuarioId = usuarioId;
        this.clase = clase;
        this.fecha = fecha;
        this.hora = hora;
        this.horarioClase = horarioClase;
    }
}
