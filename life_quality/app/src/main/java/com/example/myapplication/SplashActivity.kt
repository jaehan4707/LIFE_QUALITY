package com.example.myapplication

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.gun0912.tedpermission.PermissionListener
import kotlinx.coroutines.*
import java.io.FileInputStream

class SplashActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    val TAG = "SplashActivity"
    companion object {
        lateinit var databaseReference: DatabaseReference
        //lateinit var authReference: FirebaseAuth
        lateinit var fcmReference: FirebaseMessaging
        var token : String? = null
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getToken()
        fcmReference = FirebaseMessaging.getInstance()
        Log.d("problem","fcm : ${fcmReference.toString()}")
        activityScope.launch {
            delay(1000)
            //val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
           finish()
        }
    }

    private fun getToken(): String?{

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {
            task -> if(!task.isSuccessful){
                Log.w("problem","FCM 토큰 등록 실패",task.exception)
                return@OnCompleteListener
        }
            token = task.result


        })

        return  token
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}