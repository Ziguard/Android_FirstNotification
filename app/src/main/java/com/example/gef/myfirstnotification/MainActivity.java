package com.example.gef.myfirstnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String ID_CHANNEL="1234";
    private static final int ID_NOTIFICATION = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // cas Android 8 et plus :
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager != null)
        {
            // description du groupe :
            NotificationChannel channel = new NotificationChannel(ID_CHANNEL, getString(R.string.main_nom_channel),
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription(getString(R.string.main_description_channel));
            channel.enableLights(true);

            // comportement des notifications du groupe :
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300});
            manager.createNotificationChannel(channel);
        }
    }

    public void onClickBoutonAfficherDetail(View view) {

        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this , ID_CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_background) // a remplacer par l'icone de notification
                .setContentTitle(getString(R.string.main_nom_notification))
                .setAutoCancel(true)
                .setContentText(getString(R.string.main_description_notification));

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DetailActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0 , PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null){
            manager.notify(ID_NOTIFICATION, builder.build());
        }
    }
}
