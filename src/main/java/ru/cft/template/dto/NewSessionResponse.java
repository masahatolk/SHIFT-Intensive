package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.entity.SessionEntity;
import ru.cft.template.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class NewSessionResponse {
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    private String token;
    @Column(name = "expiration_time")
    private LocalDate expirationTime;

    public static NewSessionResponse toModel(SessionEntity entity){
        NewSessionResponse model = new NewSessionResponse();
        model.setId(entity.getId());
        model.setUserId(entity.getUser().getId());
        model.setToken(entity.getToken());
        model.setExpirationTime(entity.getExpirationTime());
        return model;
    }
}
