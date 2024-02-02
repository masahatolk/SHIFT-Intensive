package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.entity.WalletEntity;

import java.util.UUID;

@Getter
@Setter
public class DepositResponse {

    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "wallet_id")
    private UUID walletId;
    private Long amount;

    public static DepositResponse toModel(WalletEntity entity, UUID userId) {
        DepositResponse model = new DepositResponse();
        model.setUserId(userId);
        model.setWalletId(entity.getId());
        model.setAmount(entity.getAmount());
        return model;
    }
}
