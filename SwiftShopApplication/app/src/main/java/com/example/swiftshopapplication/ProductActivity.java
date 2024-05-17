package com.example.swiftshopapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ProductActivity extends AppCompatActivity {

    private TextView productName;
    private ImageView productImage;
    private TextView productDescription;

    private TextView productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_items_layout);

        // Initializing views
        productName = findViewById(R.id.product_name);
        productImage = findViewById(R.id.product_image);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);

        productName.setText("Example Product Name");
        productDescription.setText("This is a description of the example product. It is a placeholder text.");
        productImage.setImageResource(R.drawable.nav_header_background); // Replace 'example_image' with your actual image resource name
    }
}
