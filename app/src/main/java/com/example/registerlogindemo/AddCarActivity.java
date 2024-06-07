package com.example.registerlogindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class AddCarActivity extends AppCompatActivity {

    private EditText etCarName, etCarModel, etCarYear;
    private Button btnSubmitCar;
    private String URL = "http://10.0.2.2/PHP_Android/add_car.php"; // Change to your actual URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car);

        etCarName = findViewById(R.id.etCarName);
        etCarModel = findViewById(R.id.etCarModel);
        etCarYear = findViewById(R.id.etCarYear);
        btnSubmitCar = findViewById(R.id.btnSubmitCar);

        btnSubmitCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
            }
        });
    }

    private void addCar() {
        final String carName = etCarName.getText().toString().trim();
        final String carModel = etCarModel.getText().toString().trim();
        final String carYear = etCarYear.getText().toString().trim();

        if (carName.isEmpty() || carModel.isEmpty() || carYear.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddCarActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();
                // Optionally, navigate back or clear the form
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCarActivity.this, "Failed to add car: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", carName);
                params.put("model", carModel);
                params.put("year", carYear);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
