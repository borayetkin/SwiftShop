package com.example.swiftshopapplication.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swiftshopapplication.Order;
import com.example.swiftshopapplication.OrderAdapter;
import com.example.swiftshopapplication.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private ListView orderListView;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        orderListView = view.findViewById(R.id.orderListView);
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(requireContext(), orderList);
        orderListView.setAdapter(orderAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Get user's unique ID
            String userId = user.getUid();

            // Initialize the Firebase database reference to the user's orders
            databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("orders");


            // Retrieve orders from Firebase and update the list view
            readData(new FirebaseCallback() {
                @Override
                public void onCallBack(List<Order> orderList) {
                    System.out.println("ANaaaaaaaa");
                    for (int i = 0; i < orderList.size(); i++) {
                        System.out.println("Babaaaa");
                        System.out.println(orderList.get(i).getBuyerEmail());
                    }
                }
            });


            // Retrieve orders from Firebase and update the list view

        }
        return view;
    }

    private void readData(FirebaseCallback callBack) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    orderList.add(order);
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private interface FirebaseCallback {
        void onCallBack(List<Order> orderList);
    }
}
