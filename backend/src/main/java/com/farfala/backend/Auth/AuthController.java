package com.farfala.backend.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            logger.info("Intentando registrar usuario con email: {}", request.getEmail());
            AuthResponse response = authService.register(request);
            logger.info("Usuario registrado exitosamente con email: {}", request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error registrando usuario: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(new HashMap<String, String>() {{
                    put("error", e.getMessage());
                }});
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        logger.info("Intento de login con email: {}", request.getEmail());
        return ResponseEntity.ok(authService.login(request));
    }
}
