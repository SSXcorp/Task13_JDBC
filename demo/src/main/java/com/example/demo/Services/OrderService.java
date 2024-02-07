package com.example.demo.Services;

import com.example.demo.Dao.OrdersDao;
import com.example.demo.Dao.ShoppingCartDao;
import com.example.demo.Dao.UserDao;

public class OrderService {

    public static void formOrder(int userId) {
        UserDao userDao = new UserDao();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        OrdersDao ordersDao = new OrdersDao();

        // Check if the user exists
        if (!userDao.doesUserExist(userId)) {
            throw new IllegalArgumentException("User with such user_id not found");
        }

        // Check if the user has products in the shopping cart
        if (!shoppingCartDao.doesUserHaveProductsInCart(userId)) {
            throw new IllegalStateException("User with user_id " + userId + " does not have any products in the shopping cart");
        }

        String productsList = shoppingCartDao.getStringOfProductsInShoppingCart(userId);
        double totalOrder = shoppingCartDao.calculateTotalSum(userId);

        ordersDao.saveOrderFromShoppingCart(productsList, totalOrder, userId);
        shoppingCartDao.deleteAll(userId);
    }



}
