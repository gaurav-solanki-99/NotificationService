package com.example.notificationservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.notificationservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NotificationManager manager;
    public static  int notificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder nd = new NotificationCompat.Builder(MainActivity.this);
                // notificationChanel

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                {
                    String channelId="TESTID";
                    String channelName="TESTCHANNEL";
                    NotificationChannel channel=new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
                    manager.createNotificationChannel(channel);
                    nd.setChannelId(channelId);
                }
                nd.setSmallIcon(R.mipmap.ic_launcher);
                nd.setContentTitle("Test");
                nd.setContentText("Test Notification");
                Intent in = new Intent(Intent.ACTION_DIAL);
                PendingIntent pin = PendingIntent.getActivity(MainActivity.this,222,in,PendingIntent.FLAG_UPDATE_CURRENT);
                nd.addAction(R.mipmap.ic_launcher,"Call",pin);
                manager.notify(++notificationId,nd.build());

            }
        });

    }
}