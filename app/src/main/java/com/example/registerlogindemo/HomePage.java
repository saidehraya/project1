package com.example.registerlogindemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Car> carList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carList = new ArrayList<>();
//        carAdapter = new CarAdapter(carList);
        recyclerView.setAdapter(carAdapter);

        fetchCars();
    }

    private void fetchCars() {
        String url = "http://10.0.2.2/mobileProject/get_cars.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("JSON Response", response.toString()); // Log the JSON response

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject carObject = response.getJSONObject(i);

                                // Parse car data
                                int vehicleId = carObject.getInt("vehicleId");
                                String model = carObject.getString("model");
                                String name = carObject.getString("name");
                                int year = carObject.getInt("year");
                                String availableStart = carObject.getString("availableStart");
                                String availableEnd = carObject.getString("availableEnd");
                                int ownerId = carObject.getInt("ownerId");
                                int price = carObject.getInt("price");
                                String imageUrl = carObject.getString("imageUrl");

                                // Create Car object and add to list
                                Car car = new Car(name, year, price, imageUrl, vehicleId, model, availableStart, availableEnd, ownerId);
                                carList.add(car);
                            }
                            carAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HomePage.this, "Error parsing data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        String errorMessage = "Error fetching data";
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            errorMessage += ": " + new String(error.networkResponse.data);
                        }
                        Toast.makeText(HomePage.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }




}
