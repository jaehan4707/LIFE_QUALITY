package com.example.myapplication

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityAdminPlusBinding

class AdminPlus : AppCompatActivity() {
    var check : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAdminPlusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.choice.setOnClickListener {
            binding.chocieLayout.visibility= View.VISIBLE
            binding.inputLayout.visibility=View.GONE
        }
        binding.input.setOnClickListener {
            binding.chocieLayout.visibility= View.GONE
            binding.inputLayout.visibility=View.VISIBLE
        }

        binding.numInput.setOnClickListener {
            Toast.makeText(this,"입력하기버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            var num =binding.responseNum.text.toString().toInt()
            Log.d("num","${num}")
        }


        binding.time.setOnClickListener {
            Toast.makeText(this, "시간버튼을 눌렀습니다", Toast.LENGTH_SHORT).show()
            //
        }
        binding.score.setOnClickListener {
            Toast.makeText(this,"점수버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            //
        }

        binding.save.setOnClickListener {
            //저장하기 버튼을 눌렀을때
            Toast.makeText(this,"저장하기버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
    }
}