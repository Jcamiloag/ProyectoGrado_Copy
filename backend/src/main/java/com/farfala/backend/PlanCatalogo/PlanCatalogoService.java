package com.farfala.backend.PlanCatalogo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PlanCatalogoService {


    private final PlanCatalogoRepository repository;


    public List<PlanCatalogo> listar(){

        return repository.findAll();

    }


    public List<PlanCatalogo> listarActivos(){

        return repository.findByActivoTrue();

    }


    public PlanCatalogo buscarPorId(Long id){

        return repository.findById(id)
                .orElse(null);

    }


    public PlanCatalogo guardar(
            PlanCatalogo plan
    ){

        return repository.save(plan);

    }


    public PlanCatalogo actualizar(
            Long id,
            PlanCatalogo datos
    ){

        PlanCatalogo plan =
                repository.findById(id)
                .orElse(null);


        if(plan == null){

            return null;

        }


        plan.setCategoria(
                datos.getCategoria()
        );


        plan.setNombre(
                datos.getNombre()
        );


        plan.setCantidadClases(
                datos.getCantidadClases()
        );


        plan.setValor(
                datos.getValor()
        );


        plan.setDuracion(
                datos.getDuracion()
        );


        plan.setActivo(
                datos.getActivo()
        );


        return repository.save(plan);

    }


    public void eliminar(Long id){

        repository.deleteById(id);

    }

}