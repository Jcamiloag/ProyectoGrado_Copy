package com.farfala.backend.PlanCatalogo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "plan_catalogo")
public class PlanCatalogo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String categoria;


    @Column(nullable = false)
    private String nombre;


    private Integer cantidadClases;


    @Column(nullable = false)
    private Double valor;


    private Integer duracion;


    @Builder.Default
    private Boolean activo = true;

}