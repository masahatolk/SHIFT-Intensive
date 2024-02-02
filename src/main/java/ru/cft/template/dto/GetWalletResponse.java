package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.entity.WalletEntity;

import java.time.LocalDate;

@Getter
@Setter
public class GetWalletResponse {
    private String id;
    @Column(name = "user_id")
    private Long userId;
    private Long amount;
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    public static GetWalletResponse toModel(WalletEntity entity){
        GetWalletResponse model = new GetWalletResponse();
        model.setId(entity.getId());
        model.setAmount(entity.getAmount());
        model.setLastUpdate(entity.getLastUpdate());
        return model;
    }
}
