package com.jcomarella.auth.controllers;

import com.jcomarella.auth.model.User;
import com.jcomarella.auth.model.dto.login.LoginDto;
import com.jcomarella.auth.model.dto.login.TokenDto;
import com.jcomarella.auth.model.dto.users.RegisterUserDto;
import com.jcomarella.auth.services.TokenService;
import com.jcomarella.auth.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Void> getUsers(@RequestBody @Valid RegisterUserDto registerUserDto) {
        RegisterUserDto savedUser = userService.registerUser(registerUserDto);

        String location = "/users/" + savedUser.getId();
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDto(token));
    }
}
