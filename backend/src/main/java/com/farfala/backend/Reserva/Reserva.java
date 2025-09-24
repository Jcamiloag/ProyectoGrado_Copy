package com.farfala.backend.Reserva;

import java.time.LocalDate;
import java.time.LocalTime;

import com.farfala.backend.Clase.Clase;
import com.farfala.backend.Clase.HorarioClase;
import jakarta.persistence.*;
import lombok.*;

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

    private LocalDate fecha;

    private LocalTime hora;

<<<<<<< HEAD
    // ✅ Constructor personalizado SIN el id, para crear la reserva desde el controlador
    public Reserva(Long usuarioId, Clase clase, String fecha, String hora) {
=======
    // Agregamos la relación bien definida
    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private HorarioClase horarioClase;

    // ✅ Constructor personalizado SIN el id
    public Reserva(Long usuarioId, Clase clase, LocalDate fecha, LocalTime hora, HorarioClase horarioClase) {
>>>>>>> feature/reservas
        this.usuarioId = usuarioId;
        this.clase = clase;
        this.fecha = fecha;
        this.hora = hora;
        this.horarioClase = horarioClase;
    }

}
