package ru.cft.template.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSessionRequest {
    private Long phone;
    private String password;
}