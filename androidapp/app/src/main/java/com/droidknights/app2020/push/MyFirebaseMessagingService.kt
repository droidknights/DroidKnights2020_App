package com.droidknights.app2020.push

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.droidknights.app2020.R
import com.droidknights.app2020.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Timber.d("FCM_NEW_TOKEN $s")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]
        val data = remoteMessage.data["data"]
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("data", data)

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "채널"
            val channelName = "영상 업로드 알림"
            val channelMessage = NotificationChannel(
                channelId, channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "영상 콘텐츠 업로드 안내 및 라이브 시작 알림."
                enableLights(true)
                enableVibration(true)
                setShowBadge(false)
                vibrationPattern = longArrayOf(1000, 1000)
            }
            notificationManager.createNotificationChannel(channelMessage)

            val notificationBuilder =
                NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setChannelId(channelId)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)

            notificationManager.notify(9999, notificationBuilder.build())
        } else {
            val notificationBuilder =
                NotificationCompat.Builder(this, "")
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)

            notificationManager.notify(9999, notificationBuilder.build())
        }
    }
}