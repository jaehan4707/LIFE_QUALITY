package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    val TAG = "SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getToken()
        activityScope.launch {
            delay(1000)
            //val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun getToken(): String?{
        var token : String?= null
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {
            task -> if(!task.isSuccessful){
                Log.w("test","FCM 토큰 등록 실패",task.exception)
                return@OnCompleteListener
        }
            token = task.result

            Log.d("test","FCM token is ${token}")
        })
        return  token
    }
    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}