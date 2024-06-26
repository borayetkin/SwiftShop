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
    private static ProductsAdapter adapter;
    private static List<Product> products = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.productsRecyclerView;
        initializeRecyclerView();

        return root;
    }

    private void initializeRecyclerView() {
        if (products.isEmpty()){
            setProducts();
        }
        if (adapter == null){
            adapter = new ProductsAdapter(products, getContext(), false,null);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setProducts() {
        products.add(new Product("Coffee Maker", "Brews great coffee", 59.99, R.drawable.coffee_maker));
        products.add(new Product("Smartphone", "Latest Android smartphone", 799.99, R.drawable.smartphone));
        products.add(new Product("Bookshelf", "Spacious and stylish wooden bookshelf", 129.99, R.drawable.bookshelf));
        products.add(new Product("Bed", "King size luxury bed", 1199.99, R.drawable.bed));
        products.add(new Product("Fridge", "Energy-efficient double door refrigerator", 899.99, R.drawable.fridge));
        products.add(new Product("Dining Set", "Elegant dining set with 6 chairs", 699.99, R.drawable.dining_set));
        products.add(new Product("Laptop", "High performance HP laptop", 1099.99, R.drawable.laptop));
        products.add(new Product("Shoe", "AJ 1 University Blue High OG", 199.99, R.drawable.shoe));
        products.add(new Product("Television", "4K Ultra HD Smart LED TV", 499.99, R.drawable.television));
        products.add(new Product("T-shirt", "Comfortable cotton t-shirt", 29.99, R.drawable.tshirt));
        products.add(new Product("Anabolics", "Grow and shrink at the same time", 2.32, R.drawable.drugs));
        sort(true);
    }
    public static void sort(Boolean ascending){
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size()-i-1; j++) {
                if (products.get(j).getPrice() > products.get(j+1).getPrice() == ascending) {
                    Product temp = products.get(j);
                    products.set(j, products.get(j+1));
                    products.set(j+1, temp);
                }
            }
        }
        if (adapter != null) adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
