package com.example.swiftshopapplication.ui.shoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swiftshopapplication.CartManager;
import com.example.swiftshopapplication.OrdersManager;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;

import java.util.ArrayList;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView totalAmountTextView;
    private Button addToOrdersButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(new ProductsAdapter(CartManager.getInstance().getCartItems(), getContext(), true, this::updateTotal));

        totalAmountTextView = view.findViewById(R.id.totalAmountTextView);
        updateTotal(); // Initial total update

        addToOrdersButton = view.findViewById(R.id.buttonAddToOrders);
        addToOrdersButton.setOnClickListener(v -> {
            OrdersManager.getInstance().addToOrders(new ArrayList<>(CartManager.getInstance().getCartItems()));
            CartManager.getInstance().clearCart();
            recyclerView.getAdapter().notifyDataSetChanged();
            updateTotal();
        });

        return view;
    }

    private void updateTotal() {
        double total = CartManager.getInstance().calculateTotal();
        totalAmountTextView.setText(String.format("Total: $%.2f", total));
        if (CartManager.getInstance().getCartItems().size() == 0) {
            totalAmountTextView.setText("Cart is empty");
        }
    }
}