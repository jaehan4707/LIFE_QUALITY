package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity.Companion.answer
import com.example.myapplication.MainActivity.Companion.type
import com.example.myapplication.QuestionMainpage.Companion.keyList
import com.example.myapplication.databinding.ResultLayoutBinding

class ResultLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ResultLayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //여기서 점수를 쫙 계산해야함.
        Log.d("test","${answer}")
        var weight: Double = 0.0
        var flag : Int = 0
        if (type == "EQ5D") {
            for (i in 0 until keyList.size) {
                when (i) {
                    0 -> when (keyList[i].toInt()) {
                        1 -> 0
                        2 -> keyList[i].toInt() * 0.046
                        3 -> keyList[i].toInt() * 0.058
                        4 -> {
                            keyList[i].toInt() * 0.133
                            flag=1
                        }
                        5 -> {
                            keyList[i].toInt() * 0.251
                            flag=1
                        }
                    }
                    1 -> when (keyList[i].toInt()) {
                        1 -> 0
                        2 -> keyList[i].toInt() * 0.032
                        3 -> keyList[i].toInt() * 0.050
                        4 -> {
                            keyList[i].toInt() * 0.078
                            flag=1
                        }
                        5 -> {
                            keyList[i].toInt() * 0.122
                            flag=1
                        }
                    }
                    2 -> when (keyList[i].toInt()) {
                        1 -> 0
                        2 -> keyList[i].toInt() * 0.021
                        3 -> keyList[i].toInt() * 0.051
                        4 -> {
                            keyList[i].toInt() * 0.100
                            flag=1
                        }
                        5 -> {
                            keyList[i].toInt() * 0.175
                            flag=1
                        }
                    }
                    3 -> when (keyList[i].toInt()) {
                        1 -> 0
                        2 -> keyList[i].toInt() * 0.042
                        3 -> keyList[i].toInt() * 0.053
                        4 -> {
                            keyList[i].toInt() * 0.166
                            flag=1
                        }
                        5 -> {
                            keyList[i].toInt() * 0.207
                            flag=1
                        }

                    }
                    4 -> when (keyList[i].toInt()) {
                        1 -> 0
                        2 -> keyList[i].toInt() * 0.033
                        3 -> keyList[i].toInt() * 0.046
                        4 -> {
                            keyList[i].toInt() * 0.102
                            flag=1
                        }
                        5 -> {
                            keyList[i].toInt() * 0.137
                            flag=1
                        }
                    }
                }
            }
            weight= 1-(0.096+weight+0.078 * flag)
        }
        binding.nextstage.text = "처음으로"
        setContentView(binding.root)

        binding.nextstage.setOnClickListener() {
            var intent = Intent(this, QuestionSelect::class.java)
            startActivity(intent)
        }

    }
}