package com.example.demo.Dao;

import com.example.demo.ConnectionFactory;
import com.example.demo.Exceptions.SameUserEmailException;
import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.model.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsDao implements Dao<Products>{

    private Connection connection;

    public ProductsDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Optional<Products> get(long userId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?")) {
            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Products product = new Products();
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setProductName(resultSet.getString("product_name"));
                    product.setPrice(resultSet.getDouble("price"));
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting user", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Products> getAll() {
        List<Products> products = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM products ORDER BY product_id");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Products product = new Products();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products", e);
        }
        return products;
    }

    @Override
    public void save(Products product) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Products (product_name, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, product.getProductName());
            insertStatement.setDouble(2, product.getPrice());

            int affectedRows = insertStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating product", e);
        }
    }


    @Override
    public void update(Products product) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE products SET product_name = ?, price = ? WHERE product_id = ?")) {
            updateStatement.setString(1, product.getProductName());
            updateStatement.setDouble(2, product.getPrice());
            updateStatement.setInt(3, product.getProductId());

            int affectedRows = updateStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }



    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?")) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting product failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product", e);
        }
    }

}
