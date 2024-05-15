package com.example.swiftshopapplication;

import java.util.List;

public class Order {
    private List<Product> productList;
    private String address;
    private String totalPrice;
    private boolean isDelivered;
    private String buyerEmail;
    private String buyerName;

    public Order() {
        // Required empty public constructor for Firebase
    }

    public Order(List<Product> productList, String address, String totalPrice, boolean isDelivered, String buyerEmail, String buyerName) {
        this.productList = productList;
        this.address = address;
        this.totalPrice = totalPrice;
        this.isDelivered = isDelivered;
        this.buyerEmail = buyerEmail;
        this.buyerName = buyerName;
    }

    // Getters and setters
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getOrderDetails() {
        String sb = "Buyer Name: " + buyerName + " " +
                "Buyer Email: " + buyerEmail + " " + "Total Price: " + totalPrice;
        return sb;
    }
}
