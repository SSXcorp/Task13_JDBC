package com.example.demo.DaoTests;

import com.example.demo.Dao.ShoppingCartDao;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartDaoTest {

    private ShoppingCartDao shoppingCartDao;

    @BeforeEach
    public void setUp() {
        shoppingCartDao = new ShoppingCartDao();
    }

    @Test
    public void saveShoppingCartTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartId(27);
        shoppingCart.setUserId(2);
        shoppingCart.setProductId(6);

        shoppingCartDao.save(shoppingCart);

        List<ShoppingCart> userShoppingCart = shoppingCartDao.getAllProducts(2);
        assertTrue(userShoppingCart.contains(shoppingCart));
    }

    @Test
    public void deleteProductFromShoppingCartTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(2);
        shoppingCart.setProductId(6);

        shoppingCartDao.delete(2, 6);

        List<ShoppingCart> userShoppingCart = shoppingCartDao.getAllProducts(2);
        assertFalse(userShoppingCart.contains(shoppingCart));
    }

    @Test
    public void deleteAllProductsFromShoppingCartTest() {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setUserId(1);
        shoppingCart1.setProductId(2);

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setUserId(1);
        shoppingCart2.setProductId(4);

        shoppingCartDao.save(shoppingCart1);
        shoppingCartDao.save(shoppingCart2);

        shoppingCartDao.deleteAll(1);

        List<ShoppingCart> userShoppingCart = shoppingCartDao.getAllProducts(1);
        assertTrue(userShoppingCart.isEmpty());
    }

    @Test
    public void getAllProductsFromUserShoppingCartTest() {
        List<ShoppingCart> items = shoppingCartDao.getAllProducts(1);

        // Perform assertions
        assertNotNull(items);
        assertFalse(items.isEmpty());

        for (ShoppingCart item : items) {
            System.out.println("Cart ID: " + item.getCartId());
            System.out.println("User ID: " + item.getUserId());
            System.out.println("Product ID: " + item.getProductId());
            System.out.println("-------------------------");
        }

    }

    @Test
    public void getStringOfProductsInShoppingCartTest() {
        String expectedProducts = "Coffee";

        String listOfProducts = shoppingCartDao.getStringOfProductsInShoppingCart(1);

        assertEquals(expectedProducts, listOfProducts);
    }

    @Test
    public void calculateTotalSumTest() {
        double expectedTotalSum = 17.00;

        double totalSum = shoppingCartDao.calculateTotalSum(4);

        assertEquals(expectedTotalSum, totalSum);
    }

    @Test
    public void doesUserHaveProductsInCartTest() {
        assertTrue(shoppingCartDao.doesUserHaveProductsInCart(24));
        assertFalse(shoppingCartDao.doesUserHaveProductsInCart(4));
    }
}

