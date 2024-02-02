package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.NewSessionRequest;
import ru.cft.template.dto.NewSessionResponse;
import ru.cft.template.exception.UserIncorrectPassword;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.service.SessionService;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessions")
    public ResponseEntity newSession(@RequestBody NewSessionRequest body) {
        try {
            NewSessionResponse response = sessionService.newSession(body.getPhone(), body.getPassword());
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | UserIncorrectPassword e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{userId}/sessions")
    public ResponseEntity getAllSessions(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(sessionService.getAllSessions(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{userId}/sessions/current")
    public ResponseEntity newSession(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                     @PathVariable UUID userId) {
        try {
            token = token.replace("Bearer ", "");
            return ResponseEntity.ok(sessionService.getCurrentSession(token, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/sessions/{id}")
    public ResponseEntity deleteSession (@PathVariable UUID id){
        try{
            return ResponseEntity.ok(sessionService.deleteSession(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
