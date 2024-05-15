package com.example.swiftshopapplication.ui.shoppingcart;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swiftshopapplication.CartManager;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;
import com.example.swiftshopapplication.ui.checkout.CheckoutFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView totalAmountTextView;
    private EditText editTextAddress;
    private Button saveAddressButton;
    private Button addToOrdersButton;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(new ProductsAdapter(CartManager.getInstance().getCartItems(), getContext(), true, this::updateTotal));

        totalAmountTextView = view.findViewById(R.id.totalAmountTextView);
        updateTotal(); // Initial total update

        editTextAddress = view.findViewById(R.id.editTextAddress);
        saveAddressButton = view.findViewById(R.id.buttonSaveAddress);
        addToOrdersButton = view.findViewById(R.id.buttonAddToOrders);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        saveAddressButton.setOnClickListener(v -> saveAddress());

        addToOrdersButton.setOnClickListener(v -> {
            showCheckoutFragment();
            /*
            OrdersManager.getInstance().addToOrders(new ArrayList<>(CartManager.getInstance().getCartItems()));
            CartManager.getInstance().clearCart();
            recyclerView.getAdapter().notifyDataSetChanged();
            updateTotal();
             */

        });

        return view;
    }

    private void saveAddress() {
        String address = editTextAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getContext(), "Please enter an address", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.child("address").setValue(address)
                    .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Address saved", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to save address", Toast.LENGTH_SHORT).show());
        }
    }

    private void updateTotal() {
        double total = CartManager.getInstance().calculateTotal();
        totalAmountTextView.setText(String.format("Total: $%.2f", total));
        if (CartManager.getInstance().getCartItems().size() == 0) {
            totalAmountTextView.setText("Cart is empty");
        }

    }

    private void showCheckoutFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new CheckoutFragment());
        fragmentTransaction.addToBackStack(null); // Optional, if you want to add the transaction to the back stack
        fragmentTransaction.commit();

    }

}
