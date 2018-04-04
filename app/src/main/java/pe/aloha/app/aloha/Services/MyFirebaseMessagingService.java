package pe.aloha.app.aloha.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import pe.aloha.app.aloha.R;
import pe.aloha.app.aloha.UI.ListServicesActivity;

/**
 * Created by loualcala on 3/04/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        sendNotification(remoteMessage.getNotification().getBody());
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, ListServicesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "drivers_channel"; //getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Aloha,")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        // .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        // .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if(sound == null){
            Log.d(TAG, "RINGTONE: No alarm");

            sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

            if(sound == null){
                Log.d(TAG, "RINGTONE: No ringtone");

                sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }*/

        Notification notification = notificationBuilder.build();
        notification.sound = sound;
        notification.defaults |= Notification.DEFAULT_SOUND;

        notificationManager.notify(1000, notification);
    }
}
