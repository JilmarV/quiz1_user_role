package com.example.quiz.service;

import com.example.quiz.entity.Role;
import com.example.quiz.entity.User;
import com.example.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> update(User user) {
        return userRepository.findById(user.getUser_id()).map(existingUser -> {
            user.setUser_id(existingUser.getUser_id());
            User savedUser = userRepository.save(user);
            return savedUser;
        });
    }
}
