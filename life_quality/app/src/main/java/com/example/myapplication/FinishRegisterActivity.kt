package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.FinishRegisterLayoutBinding
import kotlinx.coroutines.*

class FinishRegisterActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FinishRegisterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotoLogin.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onPause() {
        super.onPause()
    }
}