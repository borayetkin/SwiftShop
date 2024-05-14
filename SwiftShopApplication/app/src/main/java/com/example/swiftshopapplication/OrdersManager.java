package com.example.swiftshopapplication;

import java.util.ArrayList;
import java.util.List;

public class OrdersManager {
    private static OrdersManager instance;
    private List<Product> orders;

    private OrdersManager() {
        orders = new ArrayList<>();
    }

    public static OrdersManager getInstance() {
        if (instance == null) {
            instance = new OrdersManager();
        }
        return instance;
    }

    public void addToOrders(List<Product> products) {
        orders.addAll(products);
    }

    public List<Product> getOrders() {
        return orders;
    }

}

