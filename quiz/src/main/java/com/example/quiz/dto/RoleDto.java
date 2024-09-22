package com.example.quiz.dto;

import com.example.quiz.entity.User;

import java.util.List;

public class RoleDto {
    private String name;
    private List<User> user;

    public RoleDto() {
    }

    public RoleDto(String name, List<User> user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
