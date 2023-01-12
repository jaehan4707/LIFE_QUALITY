package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.QuestionMainpageBinding

class QuestionMainpage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = QuestionMainpageBinding.inflate(layoutInflater)
        setContentView(binding.root);


    }
}