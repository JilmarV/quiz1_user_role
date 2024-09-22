package com.example.quiz.service;


import com.example.quiz.dao.RoleDao;
import com.example.quiz.dto.RoleDto;
import com.example.quiz.entity.Role;
import com.example.quiz.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    private RoleDao roleDao = new RoleDao();

    public List<Role> findAll() {
        return roleDao.getAllRoles();
    }

    public Optional<Role> findById(Long id) {
        return roleDao.getRoleById(id);
    }

    public RoleDto save(RoleDto role) {
        return roleDao.insertRole(role);
    }

    public void deleteById(Long id) {
        roleDao.deleteRole(id);
    }

    public Role update(Role role) {
        return roleDao.updateRole(role);
    }
}
