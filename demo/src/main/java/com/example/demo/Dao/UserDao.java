package com.example.demo.Dao;

import com.example.demo.ConnectionFactory;
import com.example.demo.Exceptions.SameUserEmailException;
import com.example.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User>{
    private Connection connection;

    public UserDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Optional<User> get(long userId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?")) {
            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setUsersurname(resultSet.getString("usersurname"));
                    user.setEmail(resultSet.getString("email"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user", e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users ORDER BY user_id");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setUsersurname(resultSet.getString("usersurname"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all users", e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        try {
            // Check if the email already exists
            try (PreparedStatement checkStatement = connection.prepareStatement("SELECT email FROM users WHERE email = ?")) {
                checkStatement.setString(1, user.getEmail());
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        throw new SameUserEmailException("User with the same email already exists.");
                    }
                }
            }

            try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users(username, usersurname, email) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                insertStatement.setString(1, user.getUsername());
                insertStatement.setString(2, user.getUsersurname());
                insertStatement.setString(3, user.getEmail());

                int affectedRows = insertStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException | SameUserEmailException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }


    @Override
    public void update(User user) {
        try {
            // Check if the email already exists for other users
            try (PreparedStatement checkEmailStatement = connection.prepareStatement("SELECT user_id FROM users WHERE email = ? AND user_id != ?")) {
                checkEmailStatement.setString(1, user.getEmail());
                checkEmailStatement.setInt(2, user.getUserId());

                try (ResultSet resultSet = checkEmailStatement.executeQuery()) {
                    if (resultSet.next()) {
                        throw new SameUserEmailException("Another user with the same email already exists.");
                    }
                }
            }

            // If email doesn't exist for other users, proceed with the update
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE users SET username = ?, usersurname = ?, email = ? WHERE userId = ?")) {
                updateStatement.setString(1, user.getUsername());
                updateStatement.setString(2, user.getUsersurname());
                updateStatement.setString(3, user.getEmail());
                updateStatement.setInt(4, user.getUserId());

                int affectedRows = updateStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Updating user failed, no rows affected.");
                }
            }
        } catch (SQLException | SameUserEmailException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }



    @Override
    public void delete(int id) { // was long, but database was already created
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?")) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    public boolean doesUserExist(long userId) {
        boolean exists = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM Users WHERE user_id = ?")) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if user exists", e);
        }
        return exists;
    }


}
