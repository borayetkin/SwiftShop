package com.example.swiftshopapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersManager {
    private static OrdersManager instance;
    private List<Product> orders;
    private DatabaseReference databaseReference;

    private OrdersManager() {
        orders = new ArrayList<>();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(auth.getCurrentUser().getUid());
            loadOrdersFromFirebase();
        }
    }

    public static OrdersManager getInstance() {
        if (instance == null) {
            instance = new OrdersManager();
        }
        return instance;
    }

    public void addToOrders(List<Product> products) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            long currentTime = System.currentTimeMillis();
            for (Product product : products) {
                Map<String, Object> orderData = new HashMap<>();
                orderData.put("product", product);
                orderData.put("orderDate", currentTime);
                databaseReference.push().setValue(orderData);
            }
            orders.addAll(products);
        }
    }

    public List<Product> getOrders() {
        return orders;
    }

    public void loadOrdersFromFirebase() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(auth.getCurrentUser().getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    orders.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Product product = snapshot.child("product").getValue(Product.class);
                        orders.add(product);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle possible errors.
                }
            });
        }
    }
}
