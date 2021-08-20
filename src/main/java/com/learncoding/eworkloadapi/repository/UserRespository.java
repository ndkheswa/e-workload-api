package com.learncoding.eworkloadapi.repository;

import com.learncoding.eworkloadapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

}
