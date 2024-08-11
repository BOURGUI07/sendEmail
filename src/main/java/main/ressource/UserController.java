/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.ressource;

import lombok.RequiredArgsConstructor;
import main.dto.UserRegistrationRequest;
import main.model.User;
import main.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest x){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerUser(x));
    }
    
    @GetMapping
    public ResponseEntity<Boolean> verifyToken(@RequestParam("token") String token){
        return ResponseEntity.status(HttpStatus.OK).body(service.verifyToken(token));
    }
}
