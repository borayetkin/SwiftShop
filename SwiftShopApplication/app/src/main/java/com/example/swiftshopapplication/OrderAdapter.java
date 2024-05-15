package com.example.swiftshopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swiftshopapplication.Order;
import com.example.swiftshopapplication.R;
import com.example.swiftshopapplication.ui.orders.OrderViewHolder;


import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    private List<Order> orderList;
    private LayoutInflater inflater;

    public OrderAdapter(Context context, List<Order> orderList) {
        super(context, R.layout.item_order, orderList);
        this.orderList = orderList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        OrderViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_order, parent, false);
            holder = new OrderViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (OrderViewHolder) view.getTag();
        }

        Order order = orderList.get(position);


        // Set other views accordingly
        holder.textProductName.setText(order.getBuyerName());
        holder.textOrderDetails.setText("Order details: " + order.getOrderDetails());

        return view;
    }
}
