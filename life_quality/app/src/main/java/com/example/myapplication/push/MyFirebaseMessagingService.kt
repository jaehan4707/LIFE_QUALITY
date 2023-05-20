package com.example.myapplication.push

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.SplashActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
class MyFirebaseMessagingService : FirebaseMessagingService(){

    private var TAG = "FirebaseMsgService"
    private var msg : String?=null
    private var title : String?= null

    override fun onNewToken(token: String) {
        // FCM 토큰이 갱신될 때 실행되는 로직을 구현합니다.
        // 토큰을 서버에 전달하거나 필요한 처리를 수행합니다.
        super.onNewToken(token)
        Log.d("problem","onNewToken: ${token}")
        val pref = this.getSharedPreferences("token",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token",token).apply()
        editor.commit()
        Log.d("problem","성공적으로 토큰을 저장함.")
        //어플리케이션 처음 실행시 토큰을 저장합니다.
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Push 알림을 수신할 때 실행되는 로직을 구현합니다.
        // 원하는 처리를 수행하고 사용자에게 알림을 표시합니다.
        Log.d("problem","onMessage : ${remoteMessage.toString()}")
        if (remoteMessage.notification != null) { //전달받은 정보에 Notification 정보가 있는 경우
            sendNotification(remoteMessage)
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "메시지를 받았습니다.", Toast.LENGTH_SHORT).show()
            }
        } else
        {
            Log.d("problem", "수신 에러 : Notification이 비었습니다")
        }
    }
    /*
    private fun sendNotification(remoteMessage: RemoteMessage){
        title = remoteMessage.notification?.title
        msg=remoteMessage.notification?.body

        Log.d("problem","title : ${title}, msg : ${msg}")
        val intent = Intent(this,SplashActivity::class.java) //push 알림을 클릭하면 해당 액티비티로 이동.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        //val contentIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)


        val mBuilder = NotificationCompat.Builder(this,"channel_id")
            .setSmallIcon(R.mipmap.ic_launcher_android)
            .setContentTitle(title)
            .setContentText(msg)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(longArrayOf(1,1000))

        val contentIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        )
        mBuilder.setContentIntent(contentIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0,mBuilder.build())

    }
     */
    private fun sendNotification(remoteMessage: RemoteMessage) {
        title = remoteMessage.notification?.title
        msg = remoteMessage.notification?.body

        Log.d("problem", "title: $title, msg: $msg")
        val intent = Intent(this, SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val mBuilder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.mipmap.ic_launcher_android)
            .setContentTitle(title)
            .setContentText(msg)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(longArrayOf(1, 1000))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "channel_id"
            val channelName = "Channel Name"
            val channelDescription = "Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            mBuilder.setChannelId(channelId)
        }

        val contentIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        )
        mBuilder.setContentIntent(contentIntent)
        val notificationId = System.currentTimeMillis().toInt()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, mBuilder.build())
    }

}