package com.farfala.backend.Evaluacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EvaluacionRepository 
extends JpaRepository<Evaluacion, Integer>{


    List<Evaluacion> findByUserId(Integer userId);


}