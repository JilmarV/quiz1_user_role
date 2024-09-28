package com.example.quiz.dto;

import com.example.quiz.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
@Valid
public class UserDto {
    @NotBlank
    private String name;
    @Size(min = 15, max = 80, message = "La dirección tiene un límite de entre 15 a 80 caracteres")
    @Pattern(regexp = "^\\d+\\s[A-Za-z0-9\\s,.#-]+$", message = "La dirección no cumple con el formato")
    private String address;
    private String phone;
    @Email
    private String email;
    private String password;
    private Role role;

    public UserDto() {
    }

    public UserDto(String name, String address, String phone, String email, String password, Role role) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
