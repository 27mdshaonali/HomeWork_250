package com.example.homework_250.bannerad;

import static com.example.homework_250.bannerad.Constant.AD_UNIT_ID;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Admob {

    public static void sdkInitialize(Context context) {
        if (!Constant.IS_AD_ENABLED) return;

        new Thread(() -> {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(context, initializationStatus -> {
            });
        }).start();

    }

    public static void setBanner(LinearLayout adContainer, Context context) {
        if (!Constant.IS_AD_ENABLED) return;


        // Create a new ad view.
        AdView adView = new AdView(context);

        adView.setAdUnitId(AD_UNIT_ID);
        adView.setAdSize(AdSize.BANNER);

        // Replace ad container with new ad view.
        adContainer.removeAllViews();
        adContainer.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

}
