package com.chess.platform.service;

import com.chess.platform.model.User;
import com.chess.platform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void deleteByUsername(String username) {
        this.userRepository.deleteByUsername(username);
    }
}
