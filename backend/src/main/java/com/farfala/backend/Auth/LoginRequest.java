package com.farfala.backend.Auth;

import lombok.AllArgsConstructor; // Importa la anotación AllArgsConstructor para generar un constructor con todos los campos
import lombok.Builder; // Importa la anotación Builder para implementar el patrón de diseño Builder
import lombok.Data; // Importa la anotación Data para generar automáticamente métodos getter, setter, equals, hashCode y toString
import lombok.NoArgsConstructor; // Importa la anotación NoArgsConstructor para generar un constructor sin argumentos

@Data // Esta anotación genera automáticamente métodos getter, setter, equals, hashCode y toString para la clase
@Builder // Esta anotación implementa el patrón de diseño Builder para la clase, permitiendo crear instancias de la clase de manera más legible y flexible
@AllArgsConstructor // Esta anotación genera un constructor con todos los campos de la clase como parámetros
@NoArgsConstructor // Esta anotación genera un constructor sin argumentos para la clase, permitiendo crear instancias de la clase sin necesidad de proporcionar valores para los campos
public class LoginRequest { // Clase que representa una solicitud de inicio de sesión
    String email;
    String password; 
}