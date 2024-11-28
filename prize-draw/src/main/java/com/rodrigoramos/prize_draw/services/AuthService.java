package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.entities.User;
import com.rodrigoramos.prize_draw.repositories.UserRepository;
import com.rodrigoramos.prize_draw.services.exceptions.InvalidTokenException;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String login(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        String token = UUID.randomUUID().toString();
        user.setToken(token);

        userRepository.save(user);

        return token;
    }

    public void logout(String token) {
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Sessão inválida."));

        user.setToken(null);
        userRepository.save(user);
    }

    public Optional<User> validateToken(String token) {
        return userRepository.findByToken(token);
    }
}
