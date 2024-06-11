package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class RegisterOwner extends AppCompatActivity {
    private EditText Fname, Lname, Cname, Owner_type, etEmailO, etPasswordO, etReenterPasswordO;
    private TextView tvStatusO;
    private Button btnRegisterO;
    //http://10.0.2.2/PHP_Android/
    private String URL = "http://10.0.2.2/mobileProject/registerOwner.php";
    private String firstName, lastName, companyName, ownerType, email, password, reenterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);

        // Initialize UI components
        Fname = findViewById(R.id.FnameO);
        Lname = findViewById(R.id.LnameO);
        Cname = findViewById(R.id.CnameO);
        Owner_type = findViewById(R.id.Owner_type);
        etEmailO = findViewById(R.id.etEmailO);
        etPasswordO = findViewById(R.id.etPasswordO);
        etReenterPasswordO = findViewById(R.id.etReenterPasswordO);
        tvStatusO = findViewById(R.id.tvStatusO);
        btnRegisterO = findViewById(R.id.btnRegisterO);

        btnRegisterO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });
    }

    public void save(View view) {
        // Get input values
        firstName = Fname.getText().toString().trim();
        lastName = Lname.getText().toString().trim();
        companyName = Cname.getText().toString().trim();
        ownerType = Owner_type.getText().toString().trim();
        email = etEmailO.getText().toString().trim();
        password = etPasswordO.getText().toString().trim();
        reenterPassword = etReenterPasswordO.getText().toString().trim();

        // Validate inputs
        if (!password.equals(reenterPassword)) {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        } else if (!firstName.equals("") && !lastName.equals("") && !companyName.equals("") && !ownerType.equals("") && !email.equals("") && !password.equals("")) {
            // Make network request to register the owner
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(RegisterOwner.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        tvStatusO.setText("Successfully registered.");
                        btnRegisterO.setClickable(false);
                    } else if (response.equals("failure")) {
                        tvStatusO.setText("Something went wrong!");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Fname", firstName);
                    data.put("Lname", lastName);
                    data.put("Cname", companyName);
                    data.put("Owner_type", ownerType);
                    data.put("email", email);
                    data.put("password", password);
                    return data;
                }
            };
            // Add the request to the request queue
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
