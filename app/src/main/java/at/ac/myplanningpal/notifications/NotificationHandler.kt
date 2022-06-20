package at.ac.myplanningpal.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.media.AudioAttributes
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


fun createNotificationChannel(channelId: String, channelName: String, soundName: String = "", context: Context) {
    val soundAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
        .build()

    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val mChannel = NotificationChannel(channelId, channelName, importance)
    mChannel.description = "My Channel MyPlanningPal"
    if (soundName.isNotEmpty()) {
        mChannel.setSound(getUriForSoundName(context, soundName), soundAttributes)
        Log.d("set sound", mChannel.toString())
    }
    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(mChannel)
}

fun simpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    Log.d("channelId", channelId)
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}

private fun getUriForSoundName(context: Context, soundName: String): Uri? {
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.packageName
                + "/raw/" + soundName
    )
}