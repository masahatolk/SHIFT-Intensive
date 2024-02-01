package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.entity.SessionEntity;

import java.time.LocalDate;

@Setter
@Getter
public class Session {
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "expiration_time")
    private LocalDate expirationTime;
    private Boolean active;

    public static Session toModel(SessionEntity entity){
        Session model = new Session();
        model.setId(entity.getId());
        model.setUserId(entity.getUser().getId());
        model.setExpirationTime(entity.getExpirationTime());
        model.setActive(!LocalDate.now().isAfter(entity.getExpirationTime()));

        return model;
    }
}
