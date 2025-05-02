package com.farfala.backend.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.farfala.backend.Jwt.JwtService;
import com.farfala.backend.User.Role;
import com.farfala.backend.User.User;
import com.farfala.backend.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService { //Clase que representa el servicio de autenticación

    private final UserRepository userRepository; //Establece la relación entre el servicio de autenticación y el repositorio de usuarios
    private final JwtService jwtService; //Establece la relación entre el servicio de autenticación y el servicio JWT
    private final PasswordEncoder passwordEncoder;//Establece la relación entre el servicio de autenticación y el codificador de contraseñas
    private final AuthenticationManager authenticationManager;//Establece la relación entre el servicio de autenticación y el administrador de autenticación

    public AuthResponse login(LoginRequest request) { //Método que maneja el inicio de sesión de un usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Role userRole = Role.USER;

        if ((request.getRole()) == Role.ADMIN) {
            userRole = Role.ADMIN;
        }
        
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.lastname)
            .email(request.getEmail())
            .phonenumber(request.getPhonenumber())
            .role(userRole)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
