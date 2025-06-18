package com.farfala.backend.Auth;


import lombok.AllArgsConstructor; //Importa todas las clases de lombok necesarias para la creación de la clase AuthResponse
import lombok.Builder; //Importa la clase Builder de lombok para la creación de objetos de la clase AuthResponse
import lombok.Data; //Importa la clase Data de lombok para la creación de la clase AuthResponse
import lombok.NoArgsConstructor; //Importa la clase NoArgsConstructor de lombok para la creación de la clase AuthResponse

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username; 
    private String role;
}