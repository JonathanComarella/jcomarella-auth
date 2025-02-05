package com.jcomarella.auth.services;

import com.jcomarella.auth.model.dto.users.RegisterUserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    RegisterUserDto registerUser(RegisterUserDto user);
}
