package com.farfala.backend.Clase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {


    List<Clase> findByCategoriaIn(
            List<String> categorias
    );


}