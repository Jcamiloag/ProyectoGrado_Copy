package com.farfala.backend.PlanCatalogo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCatalogoRepository extends JpaRepository<PlanCatalogo, Long> {

    List<PlanCatalogo> findByActivoTrue();

}