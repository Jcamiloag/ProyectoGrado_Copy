package com.farfala.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.farfala.backend.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig { //Clase de configuración de la aplicación

    private final UserRepository userRepository; //Inyeccion de dependencias del repositorio de usuarios

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception //Metodo que devuelve el administrador de autenticación
    {
        return config.getAuthenticationManager(); //Devuelve el administrador de autenticación
    }

    @Bean
    public AuthenticationProvider authenticationProvider() //Metodo que devuelve el proveedor de autenticación
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider(); //Crea un nuevo proveedor de autenticación
        authenticationProvider.setUserDetailsService(userDetailService()); //Establece el servicio de detalles del usuario
        authenticationProvider.setPasswordEncoder(passwordEncoder()); //Establece el codificador de contraseñas
        return authenticationProvider;
    }

    @Bean //Metodo que devuelve el codificador de contraseñas
    public PasswordEncoder passwordEncoder() { //Metodo que devuelve el codificador de contraseñas
        return new BCryptPasswordEncoder(); //Devuelve un nuevo codificador de contraseñas
    }

    @Bean
    public UserDetailsService userDetailService() { //Metodo que devuelve el servicio de detalles del usuario
        return username -> userRepository.findByUsername(username) //Busca el usuario por su nombre de usuario
            .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities())) //Crea un nuevo usuario de Spring Security
        .orElseThrow(()-> new UsernameNotFoundException("User not found")); //Lanza una excepción si no se encuentra el usuario
    }

}
