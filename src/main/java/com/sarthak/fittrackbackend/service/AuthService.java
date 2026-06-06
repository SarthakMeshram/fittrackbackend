package com.sarthak.fittrackbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sarthak.fittrackbackend.dto.AuthResponse;
import com.sarthak.fittrackbackend.dto.LoginRequest;
import com.sarthak.fittrackbackend.dto.RegisterRequest;
import com.sarthak.fittrackbackend.entity.User;
import com.sarthak.fittrackbackend.exception.EmailAlreadyExistsException;
import com.sarthak.fittrackbackend.exception.InvalidCredentialsException;
import com.sarthak.fittrackbackend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {

    if(userRepository.existsByEmail(request.getEmail())){
        throw new EmailAlreadyExistsException("Email already exists");
    }

    User user = new User();

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());

    user.setPassword(
            passwordEncoder.encode(
                    request.getPassword()
            )
    );

    return userRepository.save(user);
}
/* public String login(LoginRequest request) {

    User user = userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() ->
                    new InvalidCredentialsException(
                            "Invalid credentials"
                    )
            );

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    )) {

        throw new InvalidCredentialsException(
                "Invalid credentials"
        );
    }

    return "Login successful";
} */
public AuthResponse login(LoginRequest request) {

    User user = userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() ->
                    new InvalidCredentialsException(
                            "Invalid credentials"
                    )
            );

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    )) {

        throw new InvalidCredentialsException(
                "Invalid credentials"
        );
    }

    return new AuthResponse("Login successful");
}
}