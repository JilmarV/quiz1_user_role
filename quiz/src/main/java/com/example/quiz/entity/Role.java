package com.example.quiz.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column(name = "name_role")
    private String name_role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<User> user;

    public Role() {
        super();
    }

    public Role(Long role_id, String name_role, List<User> user) {
        this.role_id = role_id;
        this.name_role = name_role;
        this.user = user;
    }

    public Role(String name_role, List<User> user) {
        this.name_role = name_role;
        this.user = user;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getName_role() {
        return name_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
