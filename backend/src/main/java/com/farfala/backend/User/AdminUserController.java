package com.farfala.backend.User;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class AdminUserController {


    private final UserService userService;
    private final UserRepository userRepository;


    public AdminUserController(
            UserService userService,
            UserRepository userRepository
    ) {

        this.userService = userService;
        this.userRepository = userRepository;
    }



    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }



    @PutMapping("/{id}")
    public User updateUser(

            @PathVariable("id") Long id,

            @RequestBody User updatedUser

    ) {


        User user = userRepository
                .findById(id)
                .orElseThrow();



        user.setFirstname(
                updatedUser.getFirstname()
        );


        user.setLastname(
                updatedUser.getLastname()
        );


        user.setEmail(
                updatedUser.getEmail()
        );


        user.setPhonenumber(
                updatedUser.getPhonenumber()
        );


        return userRepository.save(user);

    }



    @DeleteMapping("/{id}")
    public void deleteUser(

            @PathVariable("id") Long id

    ) {


        userRepository.deleteById(id);

    }

}