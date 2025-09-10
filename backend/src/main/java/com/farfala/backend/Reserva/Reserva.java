
// ✅ Clase ENTIDAD Reserva completa y lista
package com.farfala.backend.Reserva;

import com.farfala.backend.Clase.Clase;
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

    private String fecha;

    private String hora;

    // ✅ Constructor personalizado SIN el id, para crear la reserva desde el controlador
    public Reserva(Long usuarioId, Clase clase, String fecha, String hora) {
        this.usuarioId = usuarioId;
        this.clase = clase;
        this.fecha = fecha;
        this.hora = hora;
    }
}