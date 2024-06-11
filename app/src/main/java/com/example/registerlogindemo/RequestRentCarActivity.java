package com.example.registerlogindemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class RequestRentCarActivity extends AppCompatActivity {

    private static final String TAG = RequestRentCarActivity.class.getSimpleName();
    private static final String URL = "http://10.0.2.2/mobileProject/get_rental_requests.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_car);

        Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<RentalRequest>>() {}.getType();
                        List<RentalRequest> rentalRequests = gson.fromJson(response.toString(), type);
                        displayRentalRequests(rentalRequests);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.getMessage());
                        Toast.makeText(RequestRentCarActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(request);
    }

    private void displayRentalRequests(List<RentalRequest> rentalRequests) {
        for (RentalRequest request : rentalRequests) {
            Log.d(TAG, "Customer ID: " + request.getCustomerId());
            Log.d(TAG, "Vehicle ID: " + request.getVehicleId());
            Log.d(TAG, "Start Date: " + request.getStartDate());
            Log.d(TAG, "Return Date: " + request.getReturnDate());
            Log.d(TAG, "Number of Days: " + request.getNumberOfDays());
            Log.d(TAG, "Number of Weeks: " + request.getNumberOfWeeks());
            Log.d(TAG, "Amount Due: " + request.getAmountDue());
            Log.d(TAG, "Active: " + request.isActive());
            Log.d(TAG, "Scheduled: " + request.isScheduled());
        }
    }
}
