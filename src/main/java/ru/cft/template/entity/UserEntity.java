package ru.cft.template.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "wallet_id")
    private String walletId = UUID.randomUUID().toString();
    private Long phone;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;
    private int age;
}