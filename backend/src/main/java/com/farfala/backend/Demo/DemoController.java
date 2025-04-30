package com.farfala.backend.Demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController //Este es un controlador REST,maneja las solicitudes HTTP y devuelve respuestas
@RequestMapping("/api/v1") //Este es el prefijo de la URL para todas las rutas de este controlador
@RequiredArgsConstructor //Esta anotación genera un constructor con los campos finales no inicializados

public class DemoController {
    @PostMapping(value = "demo") //Esta es la ruta para la solicitud de inicio de sesión
    public String welcome() {
        // Aquí iría la lógica de inicio de sesión
        return "Demo successful"; // Devuelve una respuesta de éxito
    }

}
