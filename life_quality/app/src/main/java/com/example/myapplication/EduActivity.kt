package com.example.myapplication

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.example.myapplication.databinding.ActivityEduBinding
import com.example.myapplication.databinding.EduDialogBinding
import com.example.myapplication.edu.EduEmotionActivity
import com.example.myapplication.edu.EduExcerciseActivity
import com.example.myapplication.edu.EduFallActivity
import com.example.myapplication.edu.EduFallPtActivity
import com.example.myapplication.edu.EduMouthActivity
import com.example.myapplication.edu.EduNutritionActivity
import com.example.myapplication.edu.EduSleepActivity
import com.example.myapplication.edu.EduYosilActivity
import com.example.myapplication.edu.EduYosilTrainingActivity

class EduActivity : AppCompatActivity() {
    lateinit var first_intent : Intent
    lateinit var second_intent : Intent
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
        binding.yosil.setOnClickListener { //두개 해야하는데.
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
            val intent=Intent(this@EduActivity, EduSleepActivity::class.java)
            startActivity(intent)
        }
        binding.fall.setOnClickListener { //두갈래길\
            showDialog("fall")
        }
        binding.back.setOnClickListener { //뒤로가기.
            val intent = Intent(this@EduActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.yosil.setOnClickListener {
            showDialog("yosil")
        }
    }
    fun showDialog(str:String) {
        var dialogBinding = EduDialogBinding.inflate(layoutInflater)
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)

        if(str=="fall") {
            dialogBinding.title.text="낙상"
            dialogBinding.first.text="낙상 건강소식"
            dialogBinding.second.text="낙상 예방체조"
            first_intent=Intent(this@EduActivity,EduFallActivity::class.java)
            second_intent=Intent(this@EduActivity,EduFallPtActivity::class.java)
        }
        if(str=="yosil"){
            dialogBinding.title.text="요실금"
            dialogBinding.first.text="골반훈련"
            dialogBinding.second.text="방광훈련"
            first_intent=Intent(this@EduActivity,EduYosilActivity::class.java)
            second_intent=Intent(this@EduActivity,EduYosilTrainingActivity::class.java)
        }
        dialogBinding.first.setOnClickListener{
            startActivity(first_intent)
            dialog.dismiss()
        }
        dialogBinding.second.setOnClickListener {
            startActivity(second_intent)
            dialog.dismiss()
        }
        dialogBinding.close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
        dialog.window?.setLayout(1000, 1000)
    }

    override fun onBackPressed() {
        val intent = Intent(this@EduActivity, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}