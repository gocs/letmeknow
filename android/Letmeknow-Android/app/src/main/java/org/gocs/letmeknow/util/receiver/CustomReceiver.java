package org.gocs.letmeknow.util.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.avos.avoscloud.AVOSCloud;

import org.gocs.letmeknow.R;
import org.gocs.letmeknow.activity.MainActivity;
import org.gocs.letmeknow.activity.NotificationDetailActivity;
import org.gocs.letmeknow.service.NotificationPersistService;
import org.json.JSONObject;

import static org.gocs.letmeknow.util.manager.couchbase.DataBaseClient.getPushReplication;

public class CustomReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    try {
      if (intent.getAction().equals("com.pushdemo.action")) {
        JSONObject json = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
        String dbNotificationId = json.getString("notificationId");
        final String message = json.getString("alert");
        Intent resultIntent = new Intent(AVOSCloud.applicationContext, NotificationDetailActivity.class);
        resultIntent.putExtra("notificationId", dbNotificationId);
        PendingIntent pendingIntent =
            PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(AVOSCloud.applicationContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(
                    AVOSCloud.applicationContext.getResources().getString(R.string.app_name))
                .setContentText(message)
                .setTicker(message);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);


        int mNotificationId = 10086;
        NotificationManager mNotifyMgr =
            (NotificationManager) AVOSCloud.applicationContext
                .getSystemService(
                    Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
      }
    } catch (Exception e) {

    }
  }
}
