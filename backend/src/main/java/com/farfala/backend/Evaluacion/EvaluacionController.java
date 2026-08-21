package com.farfala.backend.Evaluacion;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluaciones")
@CrossOrigin
public class EvaluacionController {

    private final EvaluacionService service;

    public EvaluacionController(EvaluacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody Evaluacion evaluacion) {

        System.out.println("========== NUEVA EVALUACION ==========");

        if (evaluacion.getUser() == null) {
            System.out.println("USER ES NULL");
        } else {
            System.out.println("USER ID: " + evaluacion.getUser().getId());
            System.out.println("EMAIL: " + evaluacion.getUser().getEmail());
        }

        System.out.println("EDAD: " + evaluacion.getEdad());
        System.out.println("PESO: " + evaluacion.getPeso());

        service.guardar(evaluacion);

        return ResponseEntity.ok("Evaluación guardada correctamente");
    }

    @GetMapping
    public List<Evaluacion> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{id}")
    public List<Evaluacion> buscarUsuario(@PathVariable Integer id) {
        return service.buscarPorUsuario(id);
    }
    
    @PutMapping("/{id}")
public ResponseEntity<String> actualizar(
        @PathVariable Integer id,
        @RequestBody Evaluacion evaluacion) {

    service.actualizar(id, evaluacion);

    return ResponseEntity.ok("Evaluación actualizada correctamente");
}

}