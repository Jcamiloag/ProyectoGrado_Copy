package com.farfala.backend.Auth;

import com.farfala.backend.User.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {
    String username;
    String password;
    String firstname;
    String lastname;
    String email; 
    String phonenumber;
    Role role;
}