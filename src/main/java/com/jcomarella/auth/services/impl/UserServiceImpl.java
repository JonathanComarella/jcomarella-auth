package com.jcomarella.auth.services.impl;

import com.jcomarella.auth.model.User;
import com.jcomarella.auth.model.dto.users.RegisterUserDto;
import com.jcomarella.auth.repositories.UserRepository;
import com.jcomarella.auth.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public RegisterUserDto registerUser(RegisterUserDto userDto) {
        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var user = modelMapper.map(userDto, User.class);

        userRepository.save(user);
        return modelMapper.map(user, RegisterUserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}