package com.jcomarella.auth.repositories;

import com.jcomarella.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    //User findUserByEmail(String email);
    Optional<User> findUserByEmail(String email);
    Boolean existsUserByEmail(String email);
}
