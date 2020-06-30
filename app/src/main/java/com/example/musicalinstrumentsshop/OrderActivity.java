package com.example.musicalinstrumentsshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musicalinstrumentsshop.models.Order;
import com.example.musicalinstrumentsshop.models.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    List<Order>orderList = new ArrayList<>();
    ListView listView;
    TextView emptyCart;
    private int status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        listView = findViewById(R.id.listView);
        emptyCart = findViewById(R.id.emptyCart);

        // creating custom adapter
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        status = intent.getIntExtra("status", 0);

        if (status == 1){
            orderList = (List<Order>) intent.getSerializableExtra("orders");
            OrderAdapter adapter = new OrderAdapter(this, R.layout.list_item, orderList);
            listView.setAdapter(adapter);
        }
        else {
            listView.setVisibility(View.GONE);
            emptyCart.setVisibility(View.VISIBLE);
        }
    }
}