package com.example.myapplication.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.EduActivity
import com.example.myapplication.LoginActivity.Companion.edu_lock
import com.example.myapplication.R
import com.example.myapplication.SplashActivity.Companion._result
import com.example.myapplication.databinding.ActivityTotalResultBinding

class TotalResultActivity : AppCompatActivity() {
    lateinit var binding : ActivityTotalResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTotalResultBinding.inflate(layoutInflater)
        edu_lock=true
        binding.eq5dResult.text=_result.eq5d
        binding.mnaResult.text= _result.mna
        binding.yosilResult.text= _result.yosil
        binding.sleephabbitResult.text= _result.sleep
        binding.fallResult.text= _result.fall
        binding.sgdskResult.text= _result.sgdsk
        binding.mouthhealthResult.text= _result.mouth
        binding.ipaqResult.text= _result.ipaq
        binding.frailtyResult.text= _result.frailty
        binding.bmiResult.text = _result.bmi
        setContentView(binding.root)

        binding.edu.setOnClickListener {
            val intent= Intent(this@TotalResultActivity,EduActivity::class.java)
            startActivity(intent)
        }
    }
}