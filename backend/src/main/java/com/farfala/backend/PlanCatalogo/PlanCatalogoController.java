package com.farfala.backend.PlanCatalogo;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/plan-catalogo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanCatalogoController {


    private final PlanCatalogoService service;



    @GetMapping
    public ResponseEntity<List<PlanCatalogo>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );

    }



    @GetMapping("/activos")
    public ResponseEntity<List<PlanCatalogo>> listarActivos() {

        return ResponseEntity.ok(
                service.listarActivos()
        );

    }



    @GetMapping("/{id}")
    public ResponseEntity<PlanCatalogo> buscar(

            @PathVariable Long id

    ) {


        PlanCatalogo plan =
                service.buscarPorId(id);



        if(plan == null){

            return ResponseEntity.notFound()
                    .build();

        }


        return ResponseEntity.ok(plan);

    }



    @PostMapping
    public ResponseEntity<PlanCatalogo> crear(

            @RequestBody PlanCatalogo plan

    ) {


        plan.setActivo(true);


        return ResponseEntity.ok(
                service.guardar(plan)
        );

    }



    @PutMapping("/{id}")
    public ResponseEntity<PlanCatalogo> actualizar(

            @PathVariable Long id,

            @RequestBody PlanCatalogo plan

    ) {


        PlanCatalogo actualizado =
                service.actualizar(
                        id,
                        plan
                );



        if(actualizado == null){

            return ResponseEntity.notFound()
                    .build();

        }



        return ResponseEntity.ok(actualizado);

    }



    @PatchMapping("/{id}/estado")
    public ResponseEntity<PlanCatalogo> cambiarEstado(

            @PathVariable Long id

    ) {


        PlanCatalogo plan =
                service.buscarPorId(id);



        if(plan == null){

            return ResponseEntity.notFound()
                    .build();

        }



        plan.setActivo(
                !plan.getActivo()
        );



        return ResponseEntity.ok(
                service.guardar(plan)
        );

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(

            @PathVariable Long id

    ) {


        PlanCatalogo plan =
                service.buscarPorId(id);



        if(plan == null){

            return ResponseEntity.notFound()
                    .build();

        }



        service.eliminar(id);



        return ResponseEntity.noContent()
                .build();

    }

}