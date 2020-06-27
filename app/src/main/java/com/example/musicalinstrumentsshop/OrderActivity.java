package com.example.musicalinstrumentsshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.musicalinstrumentsshop.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    List<Order>orderList = new ArrayList<>();
    public  static final String TAG = "SecondTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        orderList = (List<Order>) intent.getSerializableExtra("orders");
        for (Order o: orderList) {
            Log.i(TAG, o.toString());
        }
    }
}