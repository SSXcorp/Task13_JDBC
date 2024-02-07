package com.example.demo.model;

public class ShoppingCart {

    private int cartId;
    private int userId;
    private int productId;

    public ShoppingCart() {

    }

    public ShoppingCart(int cartId, int userId, int productId) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
