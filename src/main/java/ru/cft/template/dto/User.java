package ru.cft.template.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cft.template.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class User {
    private UUID id;
    @Column(name = "wallet_id")
    private UUID walletId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private Long phone;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;
    private int age;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setWalletId(entity.getWallet().getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setFullName(entity.getFirstName() + ' ' + entity.getLastName());
        model.setEmail(entity.getEmail());
        model.setPhone(entity.getPhone());
        model.setRegistrationDate(entity.getRegistrationDate());
        model.setLastUpdateDate(entity.getLastUpdateDate());
        model.setAge(entity.getAge());
        return model;
    }
}
