package com.example.demo.DaoTests;

import com.example.demo.Dao.*;
import com.example.demo.model.Orders;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrdersDaoTest {

    private OrdersDao ordersDao;

    @BeforeEach
    public void setUp() {
        ordersDao = new OrdersDao();
    }

    @Test
    public void getAllOrdersPositiveTest() {
        List<Orders> orders = ordersDao.getAll();

        assertNotNull(orders);
        assertEquals(false, orders.isEmpty());
    }

    @Test
    public void getOrdersByUserIdPositiveTest() {
        long userId = 1;
        List<Orders> orders = ordersDao.get(userId);

        assertEquals(false, orders.isEmpty());
    }

    @Test
    public void saveOrderFromShoppingCartTest() {
        String productList = "King Shrimp, Iced Tea";
        double totalAmount = 150.00;
        int userId = 1;

        ordersDao.saveOrderFromShoppingCart(productList, totalAmount, userId);

        List<Orders> orders = ordersDao.get(userId); // Fetch orders for the user and check if the newly added order exists
        boolean orderFound = orders.stream().anyMatch(order -> order.getProductList().equals(productList) && order.getTotalAmount() == totalAmount);

        assertEquals(true, orderFound);
    }
}
