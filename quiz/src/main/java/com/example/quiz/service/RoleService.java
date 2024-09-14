package com.example.quiz.service;


import com.example.quiz.entity.Role;
import com.example.quiz.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

        public Optional<Role> update(Role role) {
        return roleRepository.findById(role.getRole_id()).map(existingRole -> {
            role.setRole_id(existingRole.getRole_id());
            Role savedRole = roleRepository.save(role);
            return savedRole;
        });
    }
}
