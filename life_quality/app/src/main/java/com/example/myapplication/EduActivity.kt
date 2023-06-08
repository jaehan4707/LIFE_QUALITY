package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityEduBinding
import com.example.myapplication.databinding.EducationActivityBinding
import com.example.myapplication.edu.EduEmotionActivity
import com.example.myapplication.edu.EduExcerciseActivity
import com.example.myapplication.edu.EduMouthActivity
import com.example.myapplication.edu.EduNutritionActivity
import com.example.myapplication.edu.EduYosilActivity

class EduActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEduBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nutrition.setOnClickListener {
            val intent = Intent(this@EduActivity,EduNutritionActivity::class.java)
            startActivity(intent)
        }
        binding.mental.setOnClickListener {
            val intent=Intent(this@EduActivity,EduEmotionActivity::class.java)
            startActivity(intent)
        }
        binding.yosil.setOnClickListener {
            val intent=Intent(this@EduActivity, EduYosilActivity::class.java)
            startActivity(intent)
        }
        binding.mouth.setOnClickListener {
            val intent=Intent(this@EduActivity,EduMouthActivity::class.java)
            startActivity(intent)
        }
        binding.exercise.setOnClickListener {
            val intent=Intent(this@EduActivity,EduExcerciseActivity::class.java)
            startActivity(intent)
        }
        binding.sleep.setOnClickListener {

        }
        binding.fall.setOnClickListener {

        }
        binding.frailty.setOnClickListener {

        }
        binding.eq5d.setOnClickListener {

        }
        binding.back.setOnClickListener { //뒤로가기.
            val intent = Intent(this@EduActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@EduActivity, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}