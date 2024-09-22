package com.example.quiz.dao;

import com.example.quiz.dto.UserDto;
import com.example.quiz.entity.Role;
import com.example.quiz.entity.User;
import com.example.quiz.singleton.DataBaseSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    Connection connection;

    public UserDao() {
        this.connection = DataBaseSingleton.getInstance().getConnection();
    }

    // Insert a new user into the database
    public UserDto insertUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }

        String insertSQL = "INSERT INTO user (name, address, phone_number, email, password, role_id) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setLong(6, user.getRole() != null ? user.getRole().getRole_id() : 0); // Set default if role is null

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User insertion successful");
            } else {
                System.out.println("Failed to insert user data");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting the user into the database");
            e.printStackTrace();
        }
        return user;
    }

    // Retrieve a user from the database by user_id
    public Optional<User> getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        String selectSQL = "SELECT * FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user1 = new User();
                user1.setUser_id(rs.getLong("user_id"));
                user1.setName(rs.getString("name"));
                user1.setAddress(rs.getString("address"));
                user1.setPhone(rs.getString("phone_number"));
                user1.setEmail(rs.getString("email"));
                user1.setPassword(rs.getString("password"));
                user1.setRole(getRoleById(rs.getLong("role_id")));

                return Optional.of(user1);
            } else {
                System.out.println("User not found");
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the user");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Retrieve all users from the database
    public List<User> getAllUsers() {
        String selectSQL = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user1 = new User();
                user1.setUser_id(rs.getLong("user_id"));
                user1.setName(rs.getString("name"));
                user1.setAddress(rs.getString("address"));
                user1.setPhone(rs.getString("phone_number"));
                user1.setEmail(rs.getString("email"));
                user1.setPassword(rs.getString("password"));
                user1.setRole(getRoleById(rs.getLong("role_id")));

                userList.add(user1);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the list of users");
            e.printStackTrace();
        }
        return userList;
    }

    // Update user details in the database
    public User updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        String updateSQL = "UPDATE user SET name = ?, address = ?, phone_number = ?, email = ?, password = ?, role_id = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getAddress());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setLong(6, user.getRole() != null ? user.getRole().getRole_id() : 0);
            pstmt.setLong(7, user.getUser_id());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User update successful");
            } else {
                System.out.println("Failed to update user data");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating the user");
            e.printStackTrace();
        }
        return user;
    }

    // Delete a user from the database by user_id
    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        String deleteSQL = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setLong(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deletion successful");
            } else {
                System.out.println("Failed to delete user");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the user");
            e.printStackTrace();
        }
    }

    // Retrieve role by ID
    public Role getRoleById(Long roleId) {
        if (roleId == null) {
            throw new IllegalArgumentException("Role ID cannot be null");
        }

        String selectSQL = "SELECT * FROM role WHERE role_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setLong(1, roleId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Role role = new Role();
                role.setRole_id(rs.getLong("role_id"));
                role.setName_role(rs.getString("name_role"));
                return role;
            } else {
                System.out.println("Role not found");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error querying role");
            e.printStackTrace();
            return null;
        }
    }
}
