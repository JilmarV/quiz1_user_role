package com.example.quiz.service;

import com.example.quiz.dao.UserDao;
import com.example.quiz.dto.UserDto;
import com.example.quiz.entity.User;
import com.example.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDao userDao = new UserDao();

    public List<User> findAll() {
        return userDao.getAllUsers();
    }

    public Optional<User> findById(Long id) {
        return userDao.getUserById(id);
    }

    public UserDto save(UserDto user) {
        return userDao.insertUser(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User update(User user) {
        return userDao.updateUser(user);
    }

}
