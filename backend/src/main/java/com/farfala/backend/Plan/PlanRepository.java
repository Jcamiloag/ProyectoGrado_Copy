package com.farfala.backend.Plan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    // Obtener todos los planes de un usuario
    List<Plan> findByUserId(Long userId);

    // Buscar el plan activo del usuario según la categoría
    @Query("""
            SELECT p
            FROM Plan p
            WHERE p.user.id = :userId
            AND p.estado = 'ACTIVO'
            AND p.planCatalogo.categoria = :categoria
            """)
    Plan findPlanActivoPorUsuarioYCategoria(
            Long userId,
            String categoria
    );

    // Obtener categorías de los planes activos
    @Query("""
            SELECT p.planCatalogo.categoria
            FROM Plan p
            WHERE p.user.id = :userId
            AND p.estado = 'ACTIVO'
            """)
    List<String> findCategoriasActivasPorUsuario(
            Long userId
    );

}