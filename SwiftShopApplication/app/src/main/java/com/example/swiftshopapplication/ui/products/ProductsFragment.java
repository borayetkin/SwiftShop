package com.example.swiftshopapplication.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftshopapplication.Product;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftshopapplication.Product;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {
    private static final String ARG_CATEGORY_NAME = "categoryName";

    private String categoryName;

    public static ProductsFragment newInstance(String categoryName) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString(ARG_CATEGORY_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_products, container, false);

        // Initialize products based on the selected category
        List<Product> products = getProductsForCategory(categoryName);

        // Populate products in RecyclerView using the new ProductsAdapter
        RecyclerView recyclerView = root.findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProductsAdapter adapter = new ProductsAdapter(products, getContext(), false, null);
        recyclerView.setAdapter(adapter);

        return root;
    }

    // Method to retrieve products based on category
    private List<Product> getProductsForCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        // Simulated data for demonstration
        if (categoryName.equals("Clothes")) {
            products.add(new Product("T-Shirt", "Comfortable cotton t-shirt", 19.99, R.drawable.t_shirt));
            products.add(new Product("Jeans", "Stylish denim jeans", 39.99, R.drawable.jeans));
        } else if (categoryName.equals("Furniture")) {
            products.add(new Product("Sofa", "Cozy sofa for your living room", 299.99, R.drawable.sofa));
            products.add(new Product("Table", "Modern coffee table", 149.99, R.drawable.table));
        } else if (categoryName.equals("Electronics")) {
            products.add(new Product("Laptop", "High-performance laptop", 999.99, R.drawable.laptop));
            products.add(new Product("Headphones", "Noise-cancelling headphones", 149.99, R.drawable.headphones));
        }
        return products;
    }
}


