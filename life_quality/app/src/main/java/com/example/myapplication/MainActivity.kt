package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!@#!@12;3hl12eqlkjqwdhklqdwj;!")
        binding.qStart.setOnClickListener() {
            val intent = Intent(this, QuestionMainpage::class.java)
            startActivity(intent)
        }
        binding.redCircle.setOnClickListener{
            val intent =Intent(this,AdminHome::class.java)
            startActivity(intent)
        }

    }
}