package com.farfala.backend.Plan;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farfala.backend.PlanCatalogo.PlanCatalogo;
import com.farfala.backend.PlanCatalogo.PlanCatalogoRepository;
import com.farfala.backend.User.User;
import com.farfala.backend.User.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanController {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final PlanCatalogoRepository planCatalogoRepository;


    // =====================================
    // ASIGNAR PLAN
    // =====================================

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarPlan(
            @RequestBody AsignarPlanRequest request
    ) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PlanCatalogo planCatalogo = planCatalogoRepository.findById(request.getPlanCatalogoId())
                .orElseThrow(() -> new RuntimeException("Plan catálogo no encontrado"));

        Plan plan = Plan.builder()
                .user(user)
                .planCatalogo(planCatalogo)
                .cantidadClases(planCatalogo.getCantidadClases())
                .clasesRestantes(planCatalogo.getCantidadClases())
                .valor(planCatalogo.getValor())
                .fechaInicio(LocalDate.now())
                .fechaFinalizacion(LocalDate.now().plusMonths(1))
                .estado("ACTIVO")
                .build();

        return ResponseEntity.ok(planRepository.save(plan));
    }


    // =====================================
    // OBTENER PLANES DEL USUARIO
    // =====================================

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Plan>> obtenerPlanes(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                planRepository.findByUserId(id)
        );
    }


    // =====================================
    // ACTUALIZAR ESTADO
    // =====================================

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPlan(
            @PathVariable Long id,
            @RequestBody Plan datos
    ) {

        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        if (datos.getEstado() != null) {
            plan.setEstado(datos.getEstado());
        }

        return ResponseEntity.ok(planRepository.save(plan));
    }


    // =====================================
    // USAR CLASE
    // =====================================

    @PutMapping("/{id}/usar-clase")
    public ResponseEntity<?> usarClase(
            @PathVariable Long id
    ) {

        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        if (plan.getClasesRestantes() <= 0) {
            return ResponseEntity.badRequest()
                    .body("No tiene clases disponibles");
        }

        plan.setClasesRestantes(plan.getClasesRestantes() - 1);

        if (plan.getClasesRestantes() == 0) {
            plan.setEstado("FINALIZADO");
        }

        return ResponseEntity.ok(planRepository.save(plan));
    }


    // =====================================
    // ELIMINAR PLAN
    // =====================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPlan(
            @PathVariable Long id
    ) {

        if (!planRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        planRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}