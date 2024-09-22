package com.example.quiz.dao;

import com.example.quiz.dto.RoleDto;
import com.example.quiz.entity.Role;
import com.example.quiz.singleton.DataBaseSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao {

    Connection connection;

    public RoleDao() {
        this.connection = DataBaseSingleton.getInstance().getConnection();
    }

    // Insert a new role into the database
    public RoleDto insertRole(RoleDto roleDto) {
        if (roleDto == null) {
            throw new IllegalArgumentException("RoleDto cannot be null");
        }

        String insertSQL = "INSERT INTO role (name_role) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, roleDto.getName());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Role insertion successful");
            } else {
                System.out.println("Failed to insert role");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting the role into the database");
            e.printStackTrace();
        }
        return roleDto;
    }

    // Retrieve a role from the database by role_id
    public Optional<Role> getRoleById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        String selectSQL = "SELECT * FROM role WHERE role_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Role role1 = new Role();
                role1.setRole_id(rs.getLong("role_id"));
                role1.setName_role(rs.getString("name_role"));

                return Optional.of(role1);
            } else {
                System.out.println("Role not found");
                return Optional.empty(); // Use Optional.empty() instead of null
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the role");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Retrieve all roles from the database
    public List<Role> getAllRoles() {
        String selectSQL = "SELECT * FROM role";
        List<Role> rolesList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Role role1 = new Role();
                role1.setRole_id(rs.getLong("role_id"));
                role1.setName_role(rs.getString("name_role"));
                rolesList.add(role1);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the list of roles");
            e.printStackTrace();
        }
        return rolesList;
    }

    // Update role details in the database
    public Role updateRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        String updateSQL = "UPDATE role SET name_role = ? WHERE role_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, role.getName_role());
            pstmt.setLong(2, role.getRole_id());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Role update successful");
            } else {
                System.out.println("Failed to update role data");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating the role");
            e.printStackTrace();
        }
        return role;
    }

    // Delete a role from the database by role_id
    public void deleteRole(Long roleId) {
        if (roleId == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        String deleteSQL = "DELETE FROM role WHERE role_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setLong(1, roleId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Role deletion successful");
            } else {
                System.out.println("Failed to delete role");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the role");
            e.printStackTrace();
        }
    }
}