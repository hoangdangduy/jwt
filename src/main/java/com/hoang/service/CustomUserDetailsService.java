package com.hoang.service;

import com.hoang.config.UserPrincipal;
import com.hoang.entity.User;
import com.hoang.exception.UserNotFoundException;
import com.hoang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UserNotFoundException("Username or email: " + usernameOrEmail + " not found")
                );

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    @org.springframework.transaction.annotation.Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Id: " + id + " not found")
        );

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
