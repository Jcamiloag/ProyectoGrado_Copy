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

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), // Se usa el email como identificador en el login
                request.getPassword()
            )
        );

        User user = userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ese correo"));

        String token = jwtService.getToken(user); // JWT contiene el username real

        return AuthResponse.builder()
            .token(token)
            .username(user.getUsername()) // Se envía el username real al frontend
            .role(user.getRole().name()) // Enviamos el rol del usuario
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (request.getLastname() == null || request.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname is required");
        }
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        List<String> adminEmails = List.of(
            "admin02@farfala.com",
            "karen01@farfala.com",
            "soporte@farfala.com"
        );

        Role userRole = Role.USER;

        if (request.getEmail() != null && adminEmails.contains(request.getEmail().toLowerCase())) {
            userRole = Role.ADMIN;
        }

        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .phonenumber(request.getPhonenumber())
            .role(userRole)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .username(user.getUsername())
            .role(user.getRole().name())
            .build();
    }
}

