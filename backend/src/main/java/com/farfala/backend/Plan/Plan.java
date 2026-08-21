package com.farfala.backend.Plan;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.farfala.backend.User.User;
import com.farfala.backend.PlanCatalogo.PlanCatalogo;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plans")
@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "handler"
})
public class Plan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    @ToString.Exclude
    private User user;



    @ManyToOne
    @JoinColumn(
            name = "plan_catalogo_id",
            nullable = false
    )
    private PlanCatalogo planCatalogo;




    private Integer cantidadClases;



    private Integer clasesRestantes;



    private Double valor;



    private LocalDate fechaInicio;



    private LocalDate fechaFinalizacion;



    private String estado;


}