package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.entity.SessionEntity;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class GetSessionResponse {
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "expiration_time")
    private LocalDate expirationTime;
    private Boolean active;

    public static GetSessionResponse toModel(SessionEntity entity){
        GetSessionResponse model = new GetSessionResponse();
        model.setId(entity.getId());
        model.setUserId(entity.getUser().getId());
        model.setExpirationTime(entity.getExpirationTime());
        model.setActive(!LocalDate.now().isAfter(entity.getExpirationTime()));

        return model;
    }
}
