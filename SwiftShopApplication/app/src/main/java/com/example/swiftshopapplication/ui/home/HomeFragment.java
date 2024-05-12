package com.example.swiftshopapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftshopapplication.Product;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;
import com.example.swiftshopapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.productsRecyclerView;
        initializeRecyclerView();

        return root;
    }

    private void initializeRecyclerView() {
        List<Product> products = getProducts(); // This should return your list of products
        adapter = new ProductsAdapter(products, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Coffee Maker", "Brews great coffee", 59.99, R.drawable.coffee_maker));
        products.add(new Product("Smartphone", "Latest Android smartphone", 999.99, R.drawable.smartphone));
        products.add(new Product("Bookshelf", "Spacious and stylish", 149.99, R.drawable.bookshelf));
        // Add more products with corresponding drawable IDs
        return products;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
