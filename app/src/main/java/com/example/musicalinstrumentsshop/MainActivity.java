package com.example.musicalinstrumentsshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView quantityField;
    Button minusBtn, plusBtn;
    int quantity = 0;


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
        quantityField = findViewById(R.id.quantityField);
        minusBtn = findViewById(R.id.minusBtn);
        plusBtn = findViewById(R.id.plusBtn);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plusBtn:
                quantity++;
                quantityField.setText(Integer.toString(quantity));
                break;
            case R.id.minusBtn:
                if (quantity > 0){
                    quantity--;
                    quantityField.setText(Integer.toString(quantity));
                    break;
                }
                else if (quantity <= 0){
                    quantity = 0;
                    quantityField.setText(Integer.toString(quantity));
                    break;
                }
        }
    }
}