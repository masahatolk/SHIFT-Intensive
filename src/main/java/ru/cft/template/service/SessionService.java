package ru.cft.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.NewSessionResponse;
import ru.cft.template.dto.GetSessionResponse;
import ru.cft.template.entity.SessionEntity;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.exception.UserHasNoSessions;
import ru.cft.template.exception.UserIncorrectPassword;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.repository.SessionRepo;
import ru.cft.template.repository.UserRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SessionService {
    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private UserRepo userRepo;

    public NewSessionResponse newSession(Long phone, String password) throws UserNotFoundException, UserIncorrectPassword {
        UserEntity user = userRepo.findByPhone(phone);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с таким номером не найден");
        }
        if (!Objects.equals(user.getPassword(), password)) {
            throw new UserIncorrectPassword("Неверный пароль");
        }
        SessionEntity session = new SessionEntity();
        session.setUser(user);
        session.setExpirationTime(LocalDate.now().plusDays(1));
        sessionRepo.save(session);

        return NewSessionResponse.toModel(session);
    }

    public List<GetSessionResponse> getAllSessions(UUID userId) throws UserHasNoSessions, UserNotFoundException {
        UserEntity user = userRepo.findById(userId).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }

        List<GetSessionResponse> response = new ArrayList<>();
        Iterable<SessionEntity> sessions = sessionRepo.findAll();
        for (SessionEntity session : sessions) {
            if (Objects.equals(session.getUser().getId(), userId))
                response.add(GetSessionResponse.toModel(session));
        }

        return response;
    }

    public GetSessionResponse getCurrentSession(String token, UUID userId) throws Exception {
        SessionEntity session = sessionRepo.findByToken(token);
        if (!Objects.equals(session.getUser().getId(), userId))
            throw new Exception("Произошла ошибка");

        return GetSessionResponse.toModel(session);
    }

    public String deleteSession(UUID id){
        sessionRepo.deleteById(id);
        return "Пользователь успешно вышел из аккаунта";
    }
}