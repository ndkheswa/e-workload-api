package com.example.eworkloadapi.security;

import com.example.eworkloadapi.model.User;
import com.example.eworkloadapi.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRespository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("username or email address not found: " + usernameOrEmail)
                );
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRespository.findById(id)
                .orElseThrow(() ->
                    new UsernameNotFoundException("user with id " + id + " not found")
                );
        return UserPrincipal.create(user);
    }
}
