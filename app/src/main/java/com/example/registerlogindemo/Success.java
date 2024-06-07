package com.example.registerlogindemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Success extends AppCompatActivity {

    private Button btnAddCar;
    private Button btnUpdateCar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        btnAddCar = findViewById(R.id.btnAddCar);
        btnUpdateCar = findViewById(R.id.btnUpdateCar);

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to handle Add Car button click
                Intent intent = new Intent(Success.this, AddCarActivity.class);
                startActivity(intent);
            }
        });

        btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to handle Update Car button click
                Intent intent = new Intent(Success.this, UpdateCarActivity.class);
                startActivity(intent);
            }
        });
    }
}
