package hr.foi.air.database;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotificationClass extends FirebaseMessagingService {
    public FirebaseNotificationClass() {
        super();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

    }
}