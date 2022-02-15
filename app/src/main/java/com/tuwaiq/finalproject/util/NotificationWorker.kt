package com.tuwaiq.finalproject.util

import android.app.Notification.BADGE_ICON_LARGE
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.tuwaiq.finalproject.MainActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BADGE_ICON_LARGE
import com.tuwaiq.finalproject.CHANNEL_NOTIFICATION_ID
import com.tuwaiq.finalproject.R


class NotificationWorker(val context: Context, workerParameters: WorkerParameters): Worker(context,workerParameters) {
    override fun doWork(): Result {
        val resource = context.resources

        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)

        val notification =  NotificationCompat.Builder(context, CHANNEL_NOTIFICATION_ID)
            .setTicker(resource.getString(R.string.new_notification))
            .setSmallIcon(R.drawable.table)
            .setContentTitle(resource.getString(R.string.new_notification))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()



        val intent1 = Intent(ACTION_NOTIFICATION).apply {
            putExtra(NOTIFICATION,notification)
        }
        context.sendOrderedBroadcast(intent1, PERMISSION_PRIVAT)


        return Result.success()


    }

    companion object{
        const val ACTION_NOTIFICATION = "com.tuwaiq.finalproject.NOTIFI"
        const val PERMISSION_PRIVAT = "com.tuwaiq.finalproject.PRIVATE"
        const val NOTIFICATION = "notification"
    }
}