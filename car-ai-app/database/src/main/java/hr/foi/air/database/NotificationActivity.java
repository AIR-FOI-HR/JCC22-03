package hr.foi.air.database;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Intent intentBackgroundService = new Intent(this, FirebaseNotificationClass.class);

    @Nullable
    @Override
    public ComponentName startService(Intent intentBackgroundService) {
        return super.startService(intentBackgroundService);
    }
}
