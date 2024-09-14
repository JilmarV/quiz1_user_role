package com.example.quiz.repository;

import com.example.quiz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
