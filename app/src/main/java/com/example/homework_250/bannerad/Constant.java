package com.example.homework_250.bannerad;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Constant {
    public static String AD_ON_OFF_URL = "http://192.168.0.103/Class%20250/AdONorOFF.php";
    public static boolean IS_AD_ENABLED = true;
    public static String AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741";

    // Callback interface to notify when the ad status is fetched
    public interface AdStatusCallback {
        void onAdStatusFetched(boolean isAdEnabled);
    }

    // Method to fetch ad status and notify via callback
    public static void fetchAdStatus(Context context, AdStatusCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AD_ON_OFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                IS_AD_ENABLED = response.contains("AD_ON");
                callback.onAdStatusFetched(IS_AD_ENABLED);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                IS_AD_ENABLED = true; // Default to true if there's an error
                callback.onAdStatusFetched(IS_AD_ENABLED);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}