package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DepositRequest {

    @Column(name = "user_id")
    private UUID userId;
    private Long amount;
}

