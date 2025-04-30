package com.farfala.backend.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 

public interface UserRepository extends JpaRepository<User,Integer> { //Interfaz que extiende de JpaRepository para la gestión de usuarios en la base de datos
    Optional<User> findByUsername(String username);  //Método que busca un usuario por su nombre de usuario
}