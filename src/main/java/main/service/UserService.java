/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.service;

import lombok.RequiredArgsConstructor;
import main.dto.UserRegistrationRequest;
import main.mapper.UserMapper;
import main.model.Confirmation;
import main.model.User;
import main.repo.ConfirmationRepo;
import main.repo.UserRepo;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    private final ConfirmationRepo crepo;
    private final UserMapper mapper;
    
    
    public User registerUser(UserRegistrationRequest x){
        if(repo.existsByEmail(x.email())){
            throw new RuntimeException("email already exists");
        }
        var u = mapper.toEntity(x).setEnabled(false);
        var savedUser = repo.save(u);
        var confirmation = new Confirmation(u);
        crepo.save(confirmation);
        // todo send email to user with token
        return savedUser;
    }
    
    
    public boolean verifyToken(String token){
        var confirmation = crepo.findByToken(token);
        var opUser = repo.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        if(opUser.isPresent()){
            var user = opUser.get().setEnabled(true);
            repo.save(user);
            return true;
        }else{
            return false;
        }
    }
}
