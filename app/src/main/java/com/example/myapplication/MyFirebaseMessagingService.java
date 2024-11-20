package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;

public class MyFirebaseMessagingService {
    private static final String TAG ="MyFirebaseMsgService";


    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token:" + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {

    }
}
