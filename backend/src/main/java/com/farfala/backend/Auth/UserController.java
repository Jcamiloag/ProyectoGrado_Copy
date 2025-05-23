package com.farfala.backend.Auth;

import com.farfala.backend.Jwt.JwtService;
import com.farfala.backend.User.User;
import com.farfala.backend.User.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        String username = jwtService.getUsernameFromToken(jwt);
        User user = userRepository.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUser(
            @RequestHeader("Authorization") String token,
            @RequestBody User updatedUser) {

        String jwt = token.replace("Bearer ", "");
        String username = jwtService.getUsernameFromToken(jwt);
        User user = userRepository.findByUsername(username).orElseThrow();

        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        user.setEmail(updatedUser.getEmail());
        user.setPhonenumber(updatedUser.getPhonenumber());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}