package com.example.swiftshopapplication.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swiftshopapplication.OrdersManager;
import com.example.swiftshopapplication.ProductsAdapter;
import com.example.swiftshopapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.ordersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ProductsAdapter(OrdersManager.getInstance().getOrders(), getContext(), false, null));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            OrdersManager.getInstance().loadOrdersFromFirebase();
        }
    }
}
