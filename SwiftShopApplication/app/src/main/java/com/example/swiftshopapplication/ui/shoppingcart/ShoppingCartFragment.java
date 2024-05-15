package com.example.swiftshopapplication.ui.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swiftshopapplication.CartManager;
import com.example.swiftshopapplication.MainNavigationActivity;
import com.example.swiftshopapplication.OrdersManager;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ShoppingCartFragment extends Fragment {
    private FirebaseAuth mAuth;
    private String userId;

    private RecyclerView recyclerView;
    private Button checkoutButton, addToOrdersButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        }

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(new ProductsAdapter(CartManager.getInstance(userId).getCartItems(), getContext()));

        // Initialize Checkout Button
        checkoutButton = view.findViewById(R.id.buttonCheckout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle checkout process
                Intent intent = new Intent(getActivity(), MainNavigationActivity.class);
                startActivity(intent);
            }
        });
        addToOrdersButton = view.findViewById(R.id.buttonAddToOrders);
        addToOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add items in the cart to orders
                OrdersManager.getInstance(userId).addToOrders(CartManager.getInstance(userId).getCartItems());
                CartManager.getInstance(userId).clearCart();
                // Refresh the RecyclerView
                recyclerView.getAdapter().notifyDataSetChanged();
                Intent intent = new Intent(getActivity(), MainNavigationActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
