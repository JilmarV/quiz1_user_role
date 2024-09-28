package com.example.quiz.dto;

import com.example.quiz.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
@Valid
public class RoleDto {
    @NotBlank
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
