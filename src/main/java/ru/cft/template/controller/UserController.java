package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.exception.UserAgeIsNotAllowed;
import ru.cft.template.exception.UserAlreadyExistException;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.service.UserService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try{
            user.setRegistrationDate(LocalDate.now());
            user.setLastUpdateDate(LocalDate.now());
            userService.registration(user);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        }catch(UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(UserAgeIsNotAllowed e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id){
        try{
            return ResponseEntity.ok(userService.getOneUser(id));
        }catch(UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(userService.updateUserByFields(id, fields));
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
