package com.github.rickg2005.telecraft.service;

import com.github.rickg2005.telecraft.domain.User;
import com.github.rickg2005.telecraft.domain.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.github.rickg2005.telecraft.repository.UserRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser (String email, String rawPassword, UserRole role){
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already taken.");
        }

        String encryptedPassword = passwordEncoder.encode(rawPassword);

        User newUser = User.builder()
                .id(UUID.randomUUID())
                .email(email)
                .passwordHash(encryptedPassword)
                .role(role)
                .createdAt(OffsetDateTime.now())
                .active(true)
                .build();

        return userRepository.save(newUser);
    }

    public User loginUser (String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new RuntimeException("Invalid email or password!");
        }
        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new RuntimeException(("Invalid email or password!"));
        }

        if (!user.isActive()) {
            throw new RuntimeException("Account is deactivated!");
        }

        System.out.println("Welcome back " + user.getEmail());
        return user;
    }
}
