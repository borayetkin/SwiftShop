package com.example.swiftshopapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class OrdersManager {
    private static OrdersManager instance;
    private List<Product> orders;
    private DatabaseReference databaseReference;
    private String userId;

    private OrdersManager(String userId) {
        this.userId = userId;
        orders = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("orders").child(userId);
        loadOrdersFromFirebase();
    }

    public static OrdersManager getInstance(String userId) {
        if (instance == null || !instance.userId.equals(userId)) {
            instance = new OrdersManager(userId);
        }
        return instance;
    }

    public void addToOrders(List<Product> products) {
        orders.addAll(products);
        saveOrdersToFirebase();
    }
    private void saveOrdersToFirebase() {
        databaseReference.setValue(orders);
    }

    public List<Product> getOrders() {
        return orders;
    }
    private void loadOrdersFromFirebase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orders.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    orders.add(product);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

