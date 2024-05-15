package com.example.swiftshopapplication.ui.checkout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.swiftshopapplication.CartManager;
import com.example.swiftshopapplication.MainNavigationActivity;
import com.example.swiftshopapplication.OrderManager;
import com.example.swiftshopapplication.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    private TextView totalCostTextView;
    private TextView deliveryAddressTextView;
    private RadioGroup paymentOptions;
    private LinearLayout creditCardFields;
    private EditText cardNameEditText;
    private EditText cardNumberEditText;
    private EditText cardExpiryEditText;
    private EditText cardCvvEditText;
    private Button payButton;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        totalCostTextView = view.findViewById(R.id.totalCostTextView);
        deliveryAddressTextView = view.findViewById(R.id.deliveryAddressTextView);
        paymentOptions = view.findViewById(R.id.paymentOptions);
        creditCardFields = view.findViewById(R.id.creditCardFields);
        cardNameEditText = view.findViewById(R.id.cardNameEditText);
        cardNumberEditText = view.findViewById(R.id.cardNumberEditText);
        cardExpiryEditText = view.findViewById(R.id.cardExpiryEditText);
        cardCvvEditText = view.findViewById(R.id.cardCvvEditText);
        payButton = view.findViewById(R.id.payButton);

        // Set initial total cost
        double totalCost = CartManager.getInstance().calculateTotal();
        totalCostTextView.setText(String.format("Total: $%.2f", totalCost));

        retrieveAddress(); // Retrieve the address from Firebase

        paymentOptions.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.cashOnDeliveryOption) {
                creditCardFields.setVisibility(View.GONE); // Hide credit card fields for cash on delivery
            } else if (checkedId == R.id.creditCardOption) {
                creditCardFields.setVisibility(View.VISIBLE); // Show credit card fields for credit card option
            }
        });

        // Handle pay button click
        payButton.setOnClickListener(v -> {
            // Handle payment based on the selected option
            int selectedPaymentId = paymentOptions.getCheckedRadioButtonId();
            if (selectedPaymentId == R.id.cashOnDeliveryOption) {
                // Implement logic for cash on delivery
            } else if (selectedPaymentId == R.id.creditCardOption) {
                // Retrieve credit card information
                String cardName = cardNameEditText.getText().toString();
                String cardNumber = cardNumberEditText.getText().toString();
                String cardExpiry = cardExpiryEditText.getText().toString();
                String cardCvv = cardCvvEditText.getText().toString();

                // Implement logic for credit card payment
            }
            createOrder();

            if (checkIfPaymentCompleted()) {
                Toast.makeText(getActivity(), "Payment successful", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getActivity(), MainNavigationActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }

    private Boolean checkIfPaymentCompleted() {
        // Implement if the payment is successful or not
        return true;
    }

    private void createOrder() {
        System.out.println("AYNUR DAYANIK");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        OrderManager om = new OrderManager(getContext());
        om.placeOrder(new ArrayList<>(CartManager.getInstance().getCartItems()),
                deliveryAddressTextView.getText().toString(),
                totalCostTextView.getText().toString(),
                false,
                user.getEmail(),
                user.getDisplayName());

        CartManager.getInstance().clearCart();
    }

    private void retrieveAddress() {
        DatabaseReference addressRef = FirebaseDatabase.getInstance().getReference();

        addressRef.child("address").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.getValue(String.class);
                if (address != null) {
                    // Address retrieved successfully
                    // Update your UI or do whatever you need with the retrieved address
                    deliveryAddressTextView.setText(address);
                } else {
                    // Address is null, handle this case if necessary
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
