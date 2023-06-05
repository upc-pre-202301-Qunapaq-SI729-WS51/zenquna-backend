package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.User;
import com.qunapaq.zenquna.exception.ValidationException;
import com.qunapaq.zenquna.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zq/v1")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/users
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/users
    //Method: POST
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        existsByUsernameAndEmail(user);
        validationUser(user);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
    public void validationUser(User user) {
        if(user.getUsername()==null||user.getUsername().isEmpty()) {
            throw new ValidationException("El nombre de usuario es obligatorio");
        }
        if(user.getUsername().length()>22){
            throw new ValidationException("El nombre de usuario no puede tener más de 22 caracteres");
        }
        if(user.getEmail()==null||user.getEmail().trim().isEmpty()){
            throw new ValidationException("El correo electrónico es obligatorio");
        }
        if(user.getPassword()==null||user.getPassword().trim().isEmpty()){
            throw new ValidationException("La contraseña es obligatoria");
        }
        /*validate if userType is "donor" or "organization" cant be other */
        if(user.getUserType()==null||user.getUserType().trim().isEmpty()){
            throw new ValidationException("El tipo de usuario es obligatorio");
        }
        if (!user.getUserType().equals("donor") && !user.getUserType().equals("organization")) {
            throw new ValidationException("El tipo de usuario no es válido");
        }

    }
    public void existsByUsernameAndEmail(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new ValidationException("El nombre de usuario ya esta en uso");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ValidationException("El correo electrónico ya esta en uso");
        }
    }
}
