package com.example.registerlogindemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class Car_deitale extends AppCompatActivity {

    private String CarName, ImageUrl,mudlem;

    private ConstraintLayout available;
    private ConstraintLayout notAvailable;

    private Button book;
    private int yerss, VehicleId,availability,owner_idd;
    private Double Price;
    TextView vehicleTitle,manufacturer;
    TextView availableText,textView7,textView8;
    ImageView vehicleImage;
    TextView notAvailableText;
    TextView vehiclePrice;
    Button backButton,buttonBuy;
    TextView year;
    RadioButton genderradioButton;
    RadioGroup radioGroup;
    TextView model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_deitale);

        // Initialize views
        vehicleTitle = findViewById(R.id.vehicleTitle);
        vehiclePrice = findViewById(R.id.vehiclePrice);
        vehicleImage = findViewById(R.id.vehicleImage);
        year = findViewById(R.id.year);
        backButton = findViewById(R.id.back);
        buttonBuy = findViewById(R.id.buttonBuy);
        book = findViewById(R.id.book_this_car);
        textView7 =findViewById(R.id.textView7);
        textView8 =findViewById(R.id.textView8);

        radioGroup=(RadioGroup)findViewById(R.id.insuranceOption);

        // Initialize layouts
        availableText = findViewById(R.id.textView4);

        // Set background for the book button
        book.setBackground(ContextCompat.getDrawable(this, R.drawable.round_button));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Retrieve data from intent
        Bundle b = getIntent().getExtras();
        if (b != null) {
            CarName = b.getString("carName");
            vehicleTitle.setText(CarName);

            Price = b.getDouble("getPrice");
            vehiclePrice.setText("$" + Price + "/day");
            availability = b.getInt("getavailability");
            VehicleId = b.getInt("getVehicleId");
            yerss = b.getInt("getYear");
            year.setText(String.valueOf(yerss));
            mudlem = b.getString("getModel");
            owner_idd = b.getInt("getOwnerId");
            textView7.setText(mudlem);
            textView8.setText(CarName);

            if(availability==1){
                //
                availableText.setText("Available");
                availableText.setTextColor(Color.GREEN);
                buttonBuy.setVisibility(View.VISIBLE);
                // Set text color to green
            }else{
                //
                availableText.setText("Not Available");
                availableText.setTextColor(Color.RED);
                buttonBuy.setVisibility(View.GONE);
            }
            ImageUrl = b.getString("getImageUrl");
            Glide.with(this)
                    .load(ImageUrl)
                    .into(vehicleImage);

            // Get availability

        }



    }
    public void onBuy(View view){
        double pons;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);
        if(genderradioButton.getText().equals("None")){
            pons = 0;
        } else if (genderradioButton.getText().equals("Basic")) {
            pons = 15;
        }else{
            pons = 25;
        }
        Intent intent = new Intent(this, StartBayActrivity.class);
        intent.putExtra("carName", CarName);
        intent.putExtra("getYear", yerss);
        intent.putExtra("getPrice", Price);
        intent.putExtra("getModel", mudlem);
        intent.putExtra("getOwnerId", owner_idd);
        intent.putExtra("getVehicleId", VehicleId);
        intent.putExtra("getpons", pons);
        startActivity(intent);
    }

}
