package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginLayoutBinding
import com.example.myapplication.databinding.RegisterLayoutBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : RegisterLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegisterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.finishRegister.setOnClickListener() {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}