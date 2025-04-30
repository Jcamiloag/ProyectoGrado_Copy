package com.farfala.backend.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor; 

@RestController //Indica que esta clase es un controlador REST
@RequestMapping("/auth") //Establece la ruta base para el controlador de autenticación
@RequiredArgsConstructor //Genera un constructor con los atributos requeridos

public class AuthController { //Controlador de autenticación
    
    private final AuthService authService; //Establece la relación entre el controlador y el servicio de autenticación

    @PostMapping(value = "login") //Establece la ruta para el inicio de sesión
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) //Recibe la solicitud de inicio de sesión
    {
        return ResponseEntity.ok(authService.login(request)); //Devuelve la respuesta de autenticación
    }

    @PostMapping(value = "register") //Establece la ruta para el registro de usuarios
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}