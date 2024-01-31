package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.repository.UserRepo;

import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try{
            user.setRegistrationDate(new Date());
            user.setLastUpdateDate(new Date());
            userRepo.save(user);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getUsers(){
        try{
            return ResponseEntity.ok("Сервер работает");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
