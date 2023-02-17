package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity.Companion.answer
import com.example.myapplication.MainActivity.Companion.type
import com.example.myapplication.databinding.Eq5dResultBinding
import com.example.myapplication.databinding.FallResultBinding
import com.example.myapplication.databinding.FrailtyResultBinding
import com.example.myapplication.databinding.IpaqResultBinding
import com.example.myapplication.databinding.MnaLayoutBinding
import com.example.myapplication.databinding.MouthhealthResultBinding
import com.example.myapplication.databinding.NutritionLayoutBinding
import com.example.myapplication.databinding.ResultLayoutBinding
import com.example.myapplication.databinding.SgdskResultBinding
import com.example.myapplication.databinding.SleephabitResultBinding
import com.example.myapplication.databinding.YosilLayoutBinding

class ResultLayout : AppCompatActivity() {

     var  weight : Double=0.0
     var flag : Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //여기서 점수를 쫙 계산해야함.
        Log.d("test", "${answer}")
        weight=0.0
        flag= 0
        when (type) {
            "EQ5D" -> {
                val binding=Eq5dResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "Fall" -> {
                val binding = FallResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "SleepHabit"->{
                val binding = SleephabitResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "IPAQ" -> {
                val binding = IpaqResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "MouthHealth" -> {
                val binding = MouthhealthResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "Frailty" -> {
                val binding = FrailtyResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "SGDSK" -> {
                val binding = SgdskResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "MNA" -> {
                val binding = MnaLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "Nutrition"->{
                val binding = NutritionLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
            "Yosil" ->{
                val binding = YosilLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }

        }

        /*
        binding.nextstage.text = "처음으로"
        binding.nextstage.setOnClickListener() {
            var intent = Intent(this, QuestionSelect::class.java)
            startActivity(intent)
        }

        binding.moveHealth.setOnClickListener{
            val intent = Intent(this,CardActivity::class.java)
            startActivity(intent)
        }
        */

    }
    fun eq5d(binding: ResultLayoutBinding) {
        binding.surveyContent.setText(R.string.EQ5D)
        for (i in 0 until answer.size) {
            when (i) {
                0 -> when (answer[i].toInt()) {
                    1 -> weight += 0
                    2 -> weight += 0.046
                    3 -> weight += 0.058
                    4 -> {
                        weight += 0.133
                        flag = 1
                    }
                    5 -> {
                        weight += 0.251
                        flag = 1
                    }
                }
                1 -> when (answer[i].toInt()) {
                    1 -> weight += 0
                    2 -> weight += 0.032
                    3 -> weight += 0.050
                    4 -> {
                        weight += 0.078
                        flag = 1
                    }
                    5 -> {
                        weight += 0.122
                        flag = 1
                    }
                }
                2 -> when (answer[i].toInt()) {
                    1 -> weight += 0
                    2 -> weight += 0.021
                    3 -> weight += 0.051
                    4 -> {
                        weight += 0.100
                        flag = 1
                    }
                    5 -> {
                        weight += 0.175
                        flag = 1
                    }
                }
                3 -> when (answer[i].toInt()) {
                    1 -> weight += 0
                    2 -> weight += 0.042
                    3 -> weight += 0.053
                    4 -> {
                        weight += 0.166
                        flag = 1
                    }
                    5 -> {
                        weight += 0.207
                        flag = 1
                    }

                }
                4 -> when (answer[i].toInt()) {
                    1 -> weight += 0
                    2 -> weight += 0.033
                    3 -> weight += 0.046
                    4 -> {
                        weight += 0.102
                        flag = 1
                    }
                    5 -> {
                        weight += 0.137
                        flag = 1
                    }
                }
            }
        }
        weight = 1 - (0.096 + weight + 0.078 * flag)
        Log.d("test", "${weight}")
    }
}