package com.example.registerlogindemo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class StartBayActrivity extends AppCompatActivity {
    LinearLayout clockstart,returnDateTimeField;
    TextView pickupDate,returnDate,textView5;
    int years,months,days,yeare,monthe,daye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_bay_actrivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        clockstart = findViewById(R.id.dateTimeField);
        pickupDate = findViewById(R.id.pickupDate);
        returnDateTimeField = findViewById(R.id.returnDateTimeField);
        returnDate = findViewById(R.id.returnDate);
//        textView5 = findViewById(R.id.textView5);
//        Bundle b = getIntent().getExtras();
//        if (b != null) {
//            textView5.setText(b.getDouble("getpons")+"");
//        }

        clockstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                years = c.get(Calendar.YEAR);
                months = c.get(Calendar.MONTH);
                days = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StartBayActrivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                pickupDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },

                        years, months, days);

                datePickerDialog.show();
            }
        });

        returnDateTimeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                yeare = c.get(Calendar.YEAR);
                monthe = c.get(Calendar.MONTH);
                daye = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StartBayActrivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                returnDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },

                        yeare, monthe, daye);

                datePickerDialog.show();
            }
        });
    }
}