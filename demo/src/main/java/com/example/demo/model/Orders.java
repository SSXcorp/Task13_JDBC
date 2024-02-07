package com.example.demo.model;

public class Orders {

    private int orderId;
    private int userId;
    private String productList;
    private Double totalAmount;

    public Orders() {

    }

    public Orders(int orderId, int userId, String productList, Double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.productList = productList;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getProductList() {
        return productList;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
