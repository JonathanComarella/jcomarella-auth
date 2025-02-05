package com.jcomarella.auth.model.dto.users;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class RegisterUserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
