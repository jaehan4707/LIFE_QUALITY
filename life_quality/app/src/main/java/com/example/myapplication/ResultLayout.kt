package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ResultLayoutBinding

class ResultLayout : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ResultLayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        binding.nextstage.text = "처음으로"
        setContentView(binding.root)

        binding.nextstage.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}