package com.example.swiftshopapplication;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;
    private DatabaseReference databaseReference;
    private String userId;

    private CartManager(String userId) {
        this.userId = userId;
        cartItems = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("carts").child(userId);
        loadCartFromFirebase();
    }

    public static CartManager getInstance(String userId) {
        if (instance == null || !instance.userId.equals(userId)) {
            instance = new CartManager(userId);
        }
        return instance;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
        saveCartToFirebase();
    }

    private void saveCartToFirebase() {
        databaseReference.setValue(cartItems);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
        saveCartToFirebase();
    }
    private void loadCartFromFirebase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cartItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    cartItems.add(product);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

