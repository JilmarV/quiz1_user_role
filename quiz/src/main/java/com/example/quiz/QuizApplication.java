package com.example.quiz;

import com.example.quiz.dao.UserDao;
import com.example.quiz.dto.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
//		UserDao userDao = new UserDao();
//		UserDto userDto = new UserDto("juan", "adress01", "3215654654", "email.@gmail.com", "87654321", 1);
//		userDao.insertUser(userDto);
	}

}
