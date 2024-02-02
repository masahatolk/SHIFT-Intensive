package ru.cft.template.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sessions")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token = UUID.randomUUID().toString();
    private LocalDate expirationTime;
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
