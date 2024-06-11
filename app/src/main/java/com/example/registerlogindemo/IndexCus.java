package com.example.registerlogindemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IndexCus extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private FrameLayout framelayout;
    private BookingsFragment bookingsFragment;
    private HomePage home;
    private VehicleFragment vehicleFragment;
    TextView nnn;
    private AccountFragment accountFragment;
//    private String loggedInCustomerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_index_cus);
        Bundle b = getIntent().getExtras();
//        int userId1 = b.getInt("USER_ID");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userId = extras.getString("USER_ID");
        }
        // Retrieve user ID from Intent extras
        String userId = getIntent().getStringExtra("USER_ID");
//        nnn = findViewById(R.id.nnn);
//        nnn.setText(userId);
        // Initialize AccountFragment with user ID
        AccountFragment accountFragment = AccountFragment.newInstance(userId);
        AccountFragment vehicleFragment = AccountFragment.newInstance(userId);
        AccountFragment bookingsFragment = AccountFragment.newInstance(userId);

        // Replace the fragment container with the AccountFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, accountFragment)
                .replace(R.id.framelayout, vehicleFragment)
                .replace(R.id.framelayout, bookingsFragment)
                .commit();

        initComponents();
        clickListener();
    }

    private void clickListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_vehicle) {
                    setFragment(vehicleFragment);
                    return true;
                } else if (itemId == R.id.nav_booking) {
                    setFragment(bookingsFragment);
                    return true;
                } else if (itemId == R.id.nav_account) {
                    setFragment(accountFragment);
                    return true;
                } else {
                    throw new IllegalStateException("Unexpected value: " + itemId);
                }
//                return false;
            }
        });
    }

    private void setFragment(Fragment fragment, String Data) {
        Bundle bundle = new Bundle();
        bundle.putString("CUSTOMERID",Data);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    private void initComponents(){
        bottomNavigationView = findViewById(R.id.bottom_nav1);
        framelayout = findViewById(R.id.framelayout);

        // Initialize fragments here
        vehicleFragment = new VehicleFragment();
        bookingsFragment= new BookingsFragment();
        accountFragment = new AccountFragment();

        // Now you can set the default fragment
        setFragment(vehicleFragment);

//      loggedInCustomerID = getIntent().getStringExtra("CUSTOMERID");

    }
}