package com.example.musicalinstrumentsshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicalinstrumentsshop.models.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView quantityField, priceView;
    EditText UserName;
    Button minusBtn, plusBtn, addToCartBtn;
    Spinner spinner;
    ImageView itemImage;
    List<String> spinnerList = new ArrayList<>();
    ArrayAdapter<String> spinnerAdapter;
    HashMap<String , Double> database;
    String goodName;
    Double price;
    List<Order> orderList = new ArrayList<>();

    private int quantity = 0;
    public  static final String TAG = "FirstTAG";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_item, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variable initialization for spinner
        quantityField = findViewById(R.id.quantityField);
        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);
        priceView = findViewById(R.id.price);
        itemImage = findViewById(R.id.rockImage);
        addToCartBtn = findViewById(R.id.addtoCartbtn);
        UserName = findViewById(R.id.userName);

        // ArrayList for spinner
        spinner = findViewById(R.id.spinner);
        spinnerList.add("Guitar");
        spinnerList.add("Keyboard");
        spinnerList.add("Drums");
        spinnerList.add("Rock Guitar");

        // Adapter for spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        // Database for spinner
        database = new HashMap<>();
        database.put("Guitar", 500.0);
        database.put("Keyboard", 1000.0);
        database.put("Drums", 700.0);
        database.put("Rock Guitar", 1200.0);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        addToCartBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plusBtn:
                quantity++;
                quantityField.setText(Integer.toString(quantity));
                priceView.setText(String.valueOf(price * quantity));
                break;
            case R.id.minusBtn:
                if (quantity > 0){
                    quantity--;
                    quantityField.setText(Integer.toString(quantity));
                    priceView.setText(String.valueOf(price * quantity));
                    break;
                }
                else if (quantity <= 0){
                    quantity = 0;
                    quantityField.setText(Integer.toString(quantity));
                    priceView.setText(String.valueOf(price * quantity));
                    break;
                }
            case R.id.addtoCartbtn:
                orderProcess();
                break;
        }
    }

    public void orderProcess (){
        Order order = new Order();
        if (!TextUtils.isEmpty(UserName.getText().toString())){
            order.setUserName(UserName.getText().toString());
            order.setItemName(spinner.getSelectedItem().toString());
            order.setPrice(Double.parseDouble(priceView.getText().toString()));
            order.setQuantity(Integer.parseInt(quantityField.getText().toString()));
            orderList.add(order);

            Toast.makeText(MainActivity.this, "Item has been added to the shopping cart!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, order.toString());
        }
        else {
            Toast.makeText(MainActivity.this, "Please, fill in all fields!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodName =  spinner.getSelectedItem().toString(); //Guitar
        price = database.get(goodName);  // return 500
        priceView.setText(String.valueOf(price));
        quantity = 1;
        quantityField.setText("1");

        switch (goodName){
            case "Guitar":
                itemImage.setImageResource(R.drawable.guitar);
                break;
            case "Keyboard":
                itemImage.setImageResource(R.drawable.keyboard);
                break;
            case "Drums":
                itemImage.setImageResource(R.drawable.drums);
                break;
            case "Rock Guitar":
                itemImage.setImageResource(R.drawable.rock);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart){
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            if (!orderList.isEmpty()){
                intent.putExtra("orders", (Serializable) orderList);
                intent.putExtra("status", 1);
            }
            else {
                intent.putExtra("status", 0);
            }
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}