/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.mapper;

import main.dto.UserRegistrationRequest;
import main.model.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class UserMapper {
    public User toEntity(UserRegistrationRequest x){
        return new User().setEmail(x.email()).setName(x.name()).setPassword(x.password());
    }
}
