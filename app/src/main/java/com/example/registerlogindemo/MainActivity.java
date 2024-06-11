package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etEmaila, etPassworda;
    private String emaila, passworda;
    private String URL = "http://10.0.2.2/mobileProject/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emaila = passworda = "";
        etEmaila = findViewById(R.id.etEmaila);
        etPassworda = findViewById(R.id.etPassworda);
    }

    public void login(View view) {
        emaila = etEmaila.getText().toString().trim();
        passworda = etPassworda.getText().toString();
        if (!emaila.isEmpty() && !passworda.isEmpty()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("LoginResponse", "Response: " + response);
                    String[] parts = response.split(":");
                    Log.d("LoginResponse", "Parts Length: " + parts.length);
                    if (parts.length == 3) {
                        String userType = parts[0];
                        String status = parts[1];
                        String userId = parts[2];

                        Log.d("LoginResponse", "User Type: " + userType + ", Status: " + status + ", User ID: " + userId);

                        if (status.equals("success")) {
                            Intent intent;
                            if (userType.equals("customer")) {
                                intent = new Intent(MainActivity.this, IndexCus.class);
                            } else if (userType.equals("owner")) {
                                intent = new Intent(MainActivity.this, IndexOwner.class);
                            } else {
                                Toast.makeText(MainActivity.this, "Invalid response from server", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            intent.putExtra("USER_ID", userId);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LoginError", error.toString());
                    Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("emaila", emaila);
                    data.put("passworda", passworda);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
    public void AboutUs(View view) {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
        finish();
    }

    public void registerCompany(View view) {
        Intent intent = new Intent(this, RegisterOwner.class);
        startActivity(intent);
        finish();
    }
}
