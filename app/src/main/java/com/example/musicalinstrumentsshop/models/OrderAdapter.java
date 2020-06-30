package com.example.musicalinstrumentsshop.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicalinstrumentsshop.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    Context context;
    int resource;

    public OrderAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    // this methods calls on generating each element of the adapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String itemName = getItem(position).getItemName();  // position - is like index in arrays
        int itemQuantity = getItem(position).getQuantity();
        double itemPrice = getItem(position).getPrice();
        String itemUser = getItem(position).getUserName();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);   // this is  becoming like list_view

        TextView tvItemName = convertView.findViewById(R.id.tvItemName);
        TextView tvItemUser = convertView.findViewById(R.id.tvItemUser);
        TextView tvItemQuantity = convertView.findViewById(R.id.tvItemQuantity);
        TextView tvItemPrice = convertView.findViewById(R.id.tvItemPrice);
        ImageView ItemImage = convertView.findViewById(R.id.ItemImage);

        tvItemName.setText(itemName);
        tvItemQuantity.setText(String.valueOf(itemQuantity));
        tvItemUser.setText(itemUser);
        tvItemPrice.setText(String.valueOf(itemPrice));

        switch (itemName){
            case "Guitar":
             ItemImage.setImageResource(R.drawable.guitar);
             break;
            case "Keyboard":
                ItemImage.setImageResource(R.drawable.keyboard);
                break;
            case "Drums":
                ItemImage.setImageResource(R.drawable.drums);
                break;
            case "Rock Guitar":
                ItemImage.setImageResource(R.drawable.rock);
                break;
        }

        return convertView;

    }
}
