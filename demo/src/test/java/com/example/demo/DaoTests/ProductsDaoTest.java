package com.example.demo.DaoTests;

import com.example.demo.Dao.*;
import com.example.demo.model.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsDaoTest {

    private ProductsDao productsDao;


    @BeforeEach
    public void setUp() {
        productsDao = new ProductsDao();
    }

    @Test
    public void getProductPositiveTest() {
        // Retrieve product from the database
        Optional<Products> productOptional = productsDao.get(2);
        Products product = productOptional.orElse(null);

        // Perform assertions
        assertNotNull(product);
        assertEquals(2, product.getProductId());
        assertEquals("Grilled Steak", product.getProductName());
        assertEquals(200.00, product.getPrice());
    }

    @Test
    public void getAllProductsPositiveTest() {
        List<Products> products = productsDao.getAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());

        for (Products product : products) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("-------------------------");
        }
    }

    @Test
    public void updateProductPositiveTest() {
        // Create updated product
        Products updatedProduct = new Products();
        updatedProduct.setProductId(10);
        updatedProduct.setProductName("Bubble gum");
        updatedProduct.setPrice(3.99);

        // Update product in the database
        productsDao.update(updatedProduct);

        // Retrieve updated product from the database
        Optional<Products> productOptional = productsDao.get(10);
        Products retrievedProduct = productOptional.orElse(null);

        // Perform assertions
        assertNotNull(retrievedProduct);
        assertEquals(updatedProduct.getProductId(), retrievedProduct.getProductId());
        assertEquals(updatedProduct.getProductName(), retrievedProduct.getProductName());
        assertEquals(updatedProduct.getPrice(), retrievedProduct.getPrice());
    }

    @Test
    public void saveProductPositiveTest() {
        // Create new product to save
        Products newProduct = new Products();
        newProduct.setProductName("Gold fish");
        newProduct.setPrice(599.99);

        // Save product to the database
        productsDao.save(newProduct);

        // Retrieve saved product from the database
        Optional<Products> savedProductOptional = productsDao.get(newProduct.getProductId());
        Products savedProduct = savedProductOptional.orElse(null);


        // Perform assertions
        assertNotNull(savedProduct);
        assertEquals(newProduct.getProductName(), savedProduct.getProductName());
        assertEquals(newProduct.getPrice(), savedProduct.getPrice());
    }

    @Test
    public void deleteProductPositiveTest() {
        productsDao.delete(14);

        Optional<Products> deletedProductOptional = productsDao.get(14);

        assertFalse(deletedProductOptional.isPresent(), "Product should be deleted");
    }

}
