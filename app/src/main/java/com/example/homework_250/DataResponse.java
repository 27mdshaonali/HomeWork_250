package com.example.homework_250;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homework_250.bannerad.Admob;
import com.example.homework_250.bannerad.Constant;

public class DataResponse extends AppCompatActivity {

    public static String JSON_URL = "";
    public static String TITLE = "";
    public static Bitmap IMAGE_BITMAP = null;
    TextView resultData, titleTV;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_response);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
        volleyResponse();
    }

    //======================== Initialize Method ========================
    public void initialize() {
        resultData = findViewById(R.id.resultData);
        titleTV = findViewById(R.id.title);
        imageView = findViewById(R.id.imageView);
        resultData.setVisibility(View.GONE);
        LinearLayout adContainer = findViewById(R.id.adContainer);

        titleTV.setText(TITLE);
        imageView.setImageBitmap(IMAGE_BITMAP);

        // Initialize the Google Mobile Ads SDK and set up the banner ad
        Admob.sdkInitialize(this, new Constant.AdStatusCallback() {
            @Override
            public void onAdStatusFetched(boolean isAdEnabled) {
                if (isAdEnabled) {
                    // Set up the banner ad
                    Admob.setBanner(adContainer, DataResponse.this, new Constant.AdStatusCallback() {
                        @Override
                        public void onAdStatusFetched(boolean isAdEnabled) {
                            if (isAdEnabled) {
                                // Banner ad setup is complete
                            } else {
                                // Ads are disabled, handle accordingly
                            }
                        }
                    });
                } else {
                    // Ads are disabled, handle accordingly
                }
            }
        });
    }

    //======================== Volley Response Method ========================
    public void volleyResponse() {
        resultData.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                resultData.setText(response);
            }
        }, new Response.ErrorListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onErrorResponse(VolleyError error) {
                resultData.setText("Error");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}