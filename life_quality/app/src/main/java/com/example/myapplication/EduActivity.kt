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
            dialogBinding.first.setOnClickListener{
                Log.d("problem","낙상첫번째")
                var intent = Intent(this, EduFallActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
            dialogBinding.second.setOnClickListener {
                Log.d("problem","낙상2번")
                val intent=Intent(this, EduFallPtActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
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