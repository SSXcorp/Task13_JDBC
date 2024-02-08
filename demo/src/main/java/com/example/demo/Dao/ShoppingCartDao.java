package com.example.demo.Dao;

import com.example.demo.ConnectionFactory;
import com.example.demo.model.Orders;
import com.example.demo.model.Products;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.UserDetails;

import java.awt.font.ShapeGraphicAttribute;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDao {

    private Connection connection;

    public ShoppingCartDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void delete(int userId, int productId) { //delete single product
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM shopping_cart WHERE user_id = ? AND product_id = ?")) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }


    public void deleteAll(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM shopping_cart WHERE user_id = ?")) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    public List<ShoppingCart> getAll() { //to get all shopping cart table
        List<ShoppingCart> userDetails = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM shopping_cart");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ShoppingCart shoppingCartUnit = new ShoppingCart();
                shoppingCartUnit.setUserId(resultSet.getInt("user_id"));
                shoppingCartUnit.setCartId(resultSet.getInt("cart_id"));
                shoppingCartUnit.setProductId(resultSet.getInt("product_id"));
                userDetails.add(shoppingCartUnit);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all shopping cart details", e);
        }
        return userDetails;
    }

    public List<ShoppingCart> getAllProducts(int userId) { //to get all products of one user
        List<ShoppingCart> userShoppingCart = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM shopping_cart WHERE user_id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ShoppingCart shoppingCartUnit = new ShoppingCart();
                shoppingCartUnit.setUserId(resultSet.getInt("user_id"));
                shoppingCartUnit.setCartId(resultSet.getInt("cart_id"));
                shoppingCartUnit.setProductId(resultSet.getInt("product_id"));
                userShoppingCart.add(shoppingCartUnit);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products from the user's shopping cart", e);
        }
        return userShoppingCart;
    }


    public void save(ShoppingCart shoppingCart) { // to add product to user's shopping cart
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

    public String getStringOfProductsInShoppingCart(long userId) { //to get list of user's products as product_name String
        String listOfProducts = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT Products.product_name FROM Shopping_Cart JOIN Products ON Shopping_Cart.product_id = Products.product_id WHERE Shopping_Cart.user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listOfProducts = listOfProducts + resultSet.getString("product_name") + ", ";
            }
        listOfProducts = listOfProducts.substring(0, listOfProducts.length()-2);
        }catch(SQLException e){throw new RuntimeException("Error getting all orders of single user", e);}

        return listOfProducts;
    }

    public Double calculateTotalSum(long userId) { // to calculate total sum of all products in user's shopping cart
        double sum = 0.00;
        try (PreparedStatement statement = connection.prepareStatement("SELECT Products.price FROM Shopping_Cart JOIN Products ON Shopping_Cart.product_id = Products.product_id WHERE Shopping_Cart.user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sum = sum + resultSet.getInt("price");
            }
        }catch(SQLException e){throw new RuntimeException("Error getting all orders of single user", e);}

        return sum;
    }

    public boolean doesUserHaveProductsInCart(long userId) { // to check if user has products in user's shopping cart
        boolean hasProducts = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM Shopping_Cart WHERE user_id = ?")) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                hasProducts = resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking if user has products in cart", e);
        }
        return hasProducts;
    }



}
