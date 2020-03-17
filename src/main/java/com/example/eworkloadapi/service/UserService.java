package com.example.eworkloadapi.service;

import com.example.eworkloadapi.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    private boolean existsById(Long id) { return userRespository.existsById(id); }

    
}
