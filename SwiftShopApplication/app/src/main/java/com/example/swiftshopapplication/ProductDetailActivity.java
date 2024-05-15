package com.example.swiftshopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProductDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        } else {
            // Handle the case where the user is not authenticated
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ImageView imageView = findViewById(R.id.product_image_detail);
        TextView nameTextView = findViewById(R.id.product_name_detail);
        TextView descriptionTextView = findViewById(R.id.product_description_detail);
        TextView priceTextView = findViewById(R.id.product_price_detail);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        double price = intent.getDoubleExtra("price", 0);
        int imageResId = intent.getIntExtra("imageResId", 0);

        nameTextView.setText(name);
        descriptionTextView.setText(description);
        priceTextView.setText(String.format("$%.2f", price));
        imageView.setImageResource(imageResId);

        addToCartButton.setOnClickListener(v -> {
            Product product = new Product(name, description, price, imageResId);
            CartManager.getInstance(userId).addToCart(product);
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });

    }
}
