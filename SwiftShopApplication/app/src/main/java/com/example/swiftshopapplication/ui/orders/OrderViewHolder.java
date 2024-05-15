package com.example.swiftshopapplication.ui.orders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftshopapplication.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageProduct;
    public TextView textProductName;
    public TextView textOrderDetails;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        imageProduct = itemView.findViewById(R.id.image_product);
        textProductName = itemView.findViewById(R.id.text_product_name);
        textOrderDetails = itemView.findViewById(R.id.text_order_details);
    }
}

