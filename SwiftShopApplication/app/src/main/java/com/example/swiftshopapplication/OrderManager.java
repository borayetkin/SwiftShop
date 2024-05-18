package com.example.swiftshopapplication;

import android.content.Context;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private DatabaseReference databaseReference;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;
    private Context context;

    public OrderManager(Context context) {
        this.context = context;
        // Initializing the Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(context, orderList);
    }

    public void placeOrder(List<Product> productList, String address, String totalPrice, boolean isDelivered, String buyerEmail, String buyerName) {
        // Creating a new Order object
        Order order = new Order(productList, address, totalPrice, isDelivered, buyerEmail, buyerName);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String orderId = databaseReference.child(uid).child("orders").push().getKey();

        System.out.println("ANAN: " + orderId + " " + uid + " " + order.getTotalPrice());
        // Saving the order to Firebase using the unique key
        if (orderId != null) {
            databaseReference.child("users").child(uid).child("orders").child(orderId).setValue(order);
        }
    }
}
