package com.example.musicalinstrumentsshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.musicalinstrumentsshop.models.Order;
import com.example.musicalinstrumentsshop.models.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    List<Order>orderList = new ArrayList<>();
    List<Order>messageList = new ArrayList<>();
    ListView listView;
    TextView emptyCart;
    Button proceed;
    private int status;
    String[] addresses = {"mmh@mail.ru, murod.hodjaev@gmail.com"};
    String subject = "Musical Instruments Shop order details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        listView = findViewById(R.id.listView);
        emptyCart = findViewById(R.id.emptyCart);
        proceed = findViewById(R.id.proceed);

        // creating custom adapter
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        status = intent.getIntExtra("status", 0);
        messageList = (List<Order>) intent.getSerializableExtra("orders");

        final StringBuilder message = new StringBuilder();
        double counter = 0;

        for (Order o: messageList) {
            counter = counter + o.getPrice();
            message.append("Customer name: " + o.getUserName() + "\n" +
                    "Item name: " + o.getItemName() + "\n" +
                    "Quantity: " + o.getQuantity() + "\n" +
                    "Order price: " + o.getPrice() + "\n\n");
        }

        message.append("\n\n Final amount: " + counter);

        // proceed order button
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(message));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        if (status == 1){
            orderList = (List<Order>) intent.getSerializableExtra("orders");
            OrderAdapter adapter = new OrderAdapter(this, R.layout.list_item, orderList);
            listView.setAdapter(adapter);
        }
        else {
            listView.setVisibility(View.GONE);
            emptyCart.setVisibility(View.VISIBLE);
            proceed.setVisibility(View.GONE);
        }
    }
}