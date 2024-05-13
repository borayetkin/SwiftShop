package com.example.swiftshopapplication.ui.shoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swiftshopapplication.CartManager;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button checkoutButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(new ProductsAdapter(CartManager.getInstance().getCartItems(), getContext()));

        // Initialize Checkout Button
        checkoutButton = view.findViewById(R.id.buttonCheckout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle checkout process
                proceedToCheckout();
            }
        });

        return view;
    }

    private void proceedToCheckout() {
        // Logic to handle checkout process
        // This could navigate to a new fragment or activity
        // Example:
        // Navigation.findNavController(view).navigate(R.id.action_shoppingCartFragment_to_checkoutFragment);
    }
}
