package com.prod.app.NotificationHandler;

import android.app.NotificationManager;
import android.content.Context;

import com.google.firebase.messaging.RemoteMessage;

public class RemoteMessageNotifier {

    private Context context;

    public RemoteMessageNotifier(Context context) {
        this.context = context;
    }

    /**
     * Fire a system tray notification from the FCM message mimicking the default notification format and behavior applied when the app is in background
     *
     * @param remoteMessage FCM remote message
     * @throws IllegalArgumentException if the remote message does not contain notification information
     */
    public void notify(RemoteMessage remoteMessage) throws IllegalArgumentException {
        RemoteMessage.Notification fcmNotification = remoteMessage.getNotification();
        if (fcmNotification == null)
            throw new IllegalArgumentException(ErrorMessages.NO_NOTIFICATION_MSG);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String tag = fcmNotification.getTag();
        notificationManager.notify(tag, tag == null ? remoteMessage.getMessageId().hashCode() : 0, new RemoteMessageToNotificationMapper(context).map(remoteMessage));
    }
}
