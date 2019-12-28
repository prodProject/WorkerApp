package com.prod.app.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.prod.app.NotificationHandler.RemoteMessageNotifier;

public class AndroidFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        new RemoteMessageNotifier(getApplicationContext()).notify(remoteMessage);
    }


    @Override
    public void onNewToken(@NonNull String s) {
        Log.d("newToken", "Refreshed token: " + s);
    }
}
