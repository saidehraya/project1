package com.example.registerlogindemo;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateCarActivity extends AppCompatActivity {

    private EditText etCarId, etCarName, etCarModel, etCarYear;
    private Button btnFetchCar, btnUpdateCar;
    private String fetchURL = "http://10.0.2.2/mobileProject/get_car.php";
    private String updateURL = "http://10.0.2.2/mobileProject/update_car.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_car);

        etCarId = findViewById(R.id.etCarId);
        etCarName = findViewById(R.id.etCarName);
        etCarModel = findViewById(R.id.etCarModel);
        etCarYear = findViewById(R.id.etCarYear);
        btnFetchCar = findViewById(R.id.btnFetchCar);
        btnUpdateCar = findViewById(R.id.btnUpdateCar);


        btnFetchCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchCarDetails();
            }
        });

        btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCar();
            }
        });
    }

    private void fetchCarDetails() {
        final String id = etCarId.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Car ID is required", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, fetchURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {
                                etCarName.setText(jsonObject.getString("name"));
                                etCarModel.setText(jsonObject.getString("model"));
                                etCarYear.setText(jsonObject.getString("year"));
                            } else {
                                Toast.makeText(UpdateCarActivity.this, "Car not found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UpdateCarActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateCarActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void updateCar() {
        final String id = etCarId.getText().toString().trim();
        final String newName = etCarName.getText().toString().trim();
        final String newModel = etCarModel.getText().toString().trim();
        final String newYear = etCarYear.getText().toString().trim();

        if (id.isEmpty() || newName.isEmpty() || newModel.isEmpty() || newYear.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(UpdateCarActivity.this, "Car updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateCarActivity.this, "Failed to update car", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateCarActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", newName);
                params.put("model", newModel);
                params.put("year", newYear);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
