package com.login.william.service;

import com.login.william.dto.UserRequest;
import com.login.william.dto.UserResponse;
import com.login.william.model.UserAccount;
import com.login.william.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public UserResponse create(UserRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username já existe");
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já existe");
        }

        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setPasswordHash(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        var saved = repository.save(user);
        return new UserResponse(saved.getId(), saved.getUsername(), saved.getEmail());
    }
}
