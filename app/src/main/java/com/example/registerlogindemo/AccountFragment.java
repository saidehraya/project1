package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
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

public class AccountFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";
    private static final int REQUEST_CODE_LOGOUT = 123;

    private static String userIda;

    private EditText editTextFullName, editTextEmail, editTextPhone, editTextPassword;
    TextView idtp;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String userId) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        userIda = userId;
        args.putString(ARG_USER_ID, userIda);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userIda = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Initialize views
        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextPassword = view.findViewById(R.id.editTextPassword);
//        idtp = view.findViewById(R.id.idtp);

        // Set the user ID text
//        idtp.setText("User ID: " + userIda);

        // Fetch user details using user ID if it's not null
        if (userIda != null) {
            fetchUserDetails(userIda);
        }

        return view;
    }





    private void fetchUserDetails(String userId) {
        String URL = "http://10.0.2.2/mobileProject/getUserDetails.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            // Inside fetchUserDetails() method
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        editTextFullName.setText(jsonObject.getString("full_name"));
                        editTextEmail.setText(jsonObject.getString("email"));
                        editTextPhone.setText(jsonObject.getString("phone"));
//                        editTextPassword.setText(jsonObject.getString("password"));
                    } else {
                        Log.e("UserDetailsResponse", "Failed to fetch user details");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("UserDetailsError", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_id", userId); // Send the user ID
                return data;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void logOut() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


}
