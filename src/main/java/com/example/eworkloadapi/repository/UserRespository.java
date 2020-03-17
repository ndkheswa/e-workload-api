package com.example.eworkloadapi.repository;

import com.example.eworkloadapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
