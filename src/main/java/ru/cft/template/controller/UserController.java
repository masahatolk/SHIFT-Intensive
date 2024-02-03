package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.exception.ServiceException;
import ru.cft.template.exception.UserAlreadyExistException;
import ru.cft.template.service.UserService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Object registration(@RequestBody UserEntity user){
        try{
            userService.registerUser(user);
            return "Пользователь успешно зарегистрирован";
        }catch(UserAlreadyExistException | ServiceException e){
            return e.getMessage();
        }
        catch(Exception e){
            return "Произошла ошибка";
        }
    }

    @GetMapping("/{id}")
    public Object getOneUser(@PathVariable UUID id){
        try{
            return userService.getOneUser(id);
        }catch(ServiceException e){
            return e.getMessage();
        } catch(Exception e){
            return "Произошла ошибка";
        }
    }

    @PatchMapping("/{id}")
    public Object updateUser(@PathVariable UUID id, @RequestBody Map<String, Object> fields){
        try{
            return userService.updateUserByFields(id, fields);
        } catch(Exception e){
            return "Произошла ошибка";
        }
    }
}
