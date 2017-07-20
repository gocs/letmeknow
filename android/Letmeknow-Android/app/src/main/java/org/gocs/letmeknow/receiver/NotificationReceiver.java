package org.gocs.letmeknow.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.avos.avoscloud.AVOSCloud;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.activity.NotificationDetailActivity;
import org.json.JSONObject;

/**
 * Created by dynamicheart on 7/18/2017.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            JSONObject jsonObject = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
            String appNotificationId = jsonObject.getString("notificationId");

            Intent resultIntent = new Intent(AVOSCloud.applicationContext, NotificationDetailActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(AVOSCloud.applicationContext)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("呱呱呱")
                    .setContentText("沟里果酱生死蚁")
                    .setTicker("sdf");

            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            int notificationId = 10086;
            NotificationManager notificationManager =
                    (NotificationManager) AVOSCloud.applicationContext
                            .getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(notificationId, builder.build());


        }catch (Exception ignore){

        }
    }
}
