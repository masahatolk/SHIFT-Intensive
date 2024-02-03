package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.NewSessionRequest;
import ru.cft.template.exception.ServiceException;
import ru.cft.template.exception.UserIncorrectPassword;
import ru.cft.template.service.SessionService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessions")
    public Object newSession(@RequestBody NewSessionRequest body) {
        try {
           return sessionService.newSession(body.getPhone(), body.getPassword());
        } catch (ServiceException | UserIncorrectPassword e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/{userId}/sessions")
    public Object getAllSessions(@PathVariable UUID userId) {
        try {
            return sessionService.getAllSessions(userId);
        } catch (Exception e) {
            return "Произошла ошибка";
        }
    }

    @GetMapping("/{userId}/sessions/current")
    public Object newSession(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                             @PathVariable UUID userId) {
        try {
            //Скипаем "Bearer "
            token = token.substring(7);
            return sessionService.getCurrentSession(token, userId);
        } catch (Exception e) {
            return "Произошла ошибка";
        }
    }

    @DeleteMapping("/sessions/{id}")
    public String deleteSession(@PathVariable UUID id) {
        try {
            sessionService.deleteSession(id);
            return null;
        } catch (Exception e) {
            return "Произошла ошибка";
        }
    }
}
