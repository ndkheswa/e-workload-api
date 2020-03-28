package com.example.eworkloadapi.repository;

import com.example.eworkloadapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

}
