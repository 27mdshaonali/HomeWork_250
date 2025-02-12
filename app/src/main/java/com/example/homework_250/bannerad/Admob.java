package com.example.homework_250.bannerad;

import android.content.Context;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Admob {

    // Initialize the SDK only after ad status is fetched
    public static void sdkInitialize(Context context, Constant.AdStatusCallback callback) {
        Constant.fetchAdStatus(context, new Constant.AdStatusCallback() {
            @Override
            public void onAdStatusFetched(boolean isAdEnabled) {
                if (!isAdEnabled) {
                    callback.onAdStatusFetched(false); // Notify that ads are disabled
                    return;
                }

                // Initialize the Google Mobile Ads SDK on a background thread.
                new Thread(() -> {
                    MobileAds.initialize(context, initializationStatus -> {
                        callback.onAdStatusFetched(true); // Notify that initialization is complete
                    });
                }).start();
            }
        });
    }

    // Set up the banner only after ad status is fetched
    public static void setBanner(LinearLayout adContainer, Context context, Constant.AdStatusCallback callback) {
        Constant.fetchAdStatus(context, new Constant.AdStatusCallback() {
            @Override
            public void onAdStatusFetched(boolean isAdEnabled) {
                if (!isAdEnabled) {
                    callback.onAdStatusFetched(false); // Skip if ads are disabled
                    return;
                }

                // Create a new ad view.
                AdView adView = new AdView(context);
                adView.setAdUnitId(Constant.AD_UNIT_ID);
                adView.setAdSize(AdSize.BANNER);

                // Replace ad container with new ad view.
                adContainer.removeAllViews();
                adContainer.addView(adView);

                // Start loading the ad in the background.
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);

                callback.onAdStatusFetched(true); // Notify that banner setup is complete
            }
        });
    }
}