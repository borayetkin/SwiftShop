package com.example.swiftshopapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swiftshopapplication.Category;
import com.example.swiftshopapplication.CategoryAdapter;
import com.example.swiftshopapplication.R;
import com.example.swiftshopapplication.ui.products.ProductsFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize categories
        categories = new ArrayList<>();
        categories.add(new Category("Clothes", R.drawable.clothes_image));
        categories.add(new Category("Furniture", R.drawable.furniture_image));
        categories.add(new Category("Electronics", R.drawable.electronics_image));

        // Initialize RecyclerView
        recyclerView = root.findViewById(R.id.categories_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryAdapter = new CategoryAdapter(categories, getContext(), this);
        recyclerView.setAdapter(categoryAdapter);

        return root;
    }

    @Override
    public void onCategoryClick(Category category) {
        // Handle category click event
        // Navigate to ProductsFragment passing the selected category
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, ProductsFragment.newInstance(category.getName()));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
