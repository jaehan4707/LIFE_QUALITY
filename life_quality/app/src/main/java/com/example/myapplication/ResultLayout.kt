package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ResultLayoutBinding

class ResultLayout : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ResultLayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}