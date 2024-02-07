package com.example.demo.Dao;

import com.example.demo.ConnectionFactory;
import com.example.demo.model.Orders;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDao {
    private Connection connection;

    public OrdersDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void save(ShoppingCart shoppingCart) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Shopping_Cart (user_id, product_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, shoppingCart.getUserId());
            insertStatement.setInt(2, shoppingCart.getProductId());

            int affectedRows = insertStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating shopping cart unit failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    shoppingCart.setCartId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating shopping cart unit failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating shopping cart unit", e);
        }
    }


    public List<Orders> getAll() {
        List<Orders> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setProductList(resultSet.getString("product_list"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all orders", e);
        }
        return orders;
    }

    public List<Orders> get(long userId) {
        List<Orders> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setProductList(resultSet.getString("product_list"));
                order.setTotalAmount(resultSet.getDouble("total_amount"));
                orders.add(order);
            }
        }catch(SQLException e){throw new RuntimeException("Error getting all orders of single user", e);}

        return orders;
    }

    public void saveOrderFromShoppingCart(String productList, double totalAmount, int userId) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Orders (user_id, product_list, total_amount) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, userId);
            insertStatement.setString(2, productList);
            insertStatement.setDouble(3, totalAmount);

            int affectedRows = insertStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

        } catch (SQLException e) {throw new RuntimeException("Error creating shopping cart unit", e);}


    }

    public void saveOrderFromShoppingCart(ShoppingCart shoppingCart) {


    }


}
