package com.tuwaiq.finalproject.util

import android.app.Activity
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.tuwaiq.finalproject.util.NotificationWorker.Companion.NOTIFICATION

class NotificationReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (resultCode != Activity.RESULT_OK){return}

        val notification:Notification = intent?.getParcelableExtra(NOTIFICATION)!!

        val notificationManager = NotificationManagerCompat.from(context!!)
        notificationManager.notify(0,notification)



    }
}