package com.jcomarella.auth.services;

import com.jcomarella.auth.model.User;

public interface TokenService {

    String generateToken(User user);
    String validateToken(String token);
}
