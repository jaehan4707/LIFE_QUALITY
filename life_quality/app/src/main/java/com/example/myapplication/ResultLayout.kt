package com.example.myapplication

import android.content.Intent
import android.graphics.Color
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

    var weight: Double = 0.0
    var flag: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //여기서 점수를 쫙 계산해야함.
        Log.d("test", "${answer}")
        weight = 0.0
        flag = 0
        when (type) {
            "EQ5D" -> {
                val binding = Eq5dResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                var traffic = result(type)
                     when(traffic){
                            1->{
                                binding.redLight.setBackgroundResource(R.drawable.red_circle)
                                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text="불행하다"
                        binding.resultTxt.setTextColor(Color.parseColor("#EE3B3B"))
                    }
                    2->{
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text="노력"
                        binding.resultTxt.setTextColor(Color.parseColor("#FECA13"))
                    }
                    3->{
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                        binding.resultTxt.setTextColor(Color.parseColor("#18EA46"))
                        binding.resultTxt.text="행복"
                    }
                }
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } //완료
            "Fall" -> {
                val binding = FallResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "SleepHabit" -> {
                val binding = SleephabitResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "IPAQ" -> {
                val binding = IpaqResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                var traffic = result(type)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "MouthHealth" -> {
                val binding = MouthhealthResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                var traffic = result(type)
                when(traffic) {
                    1 -> {
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "나쁨입"
                        binding.resultTxt.setTextColor(Color.parseColor("#EE3B3B"))
                    }
                    2 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "보통"
                        binding.resultTxt.setTextColor(Color.parseColor("#FECA13"))
                    }
                    3 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                        binding.resultTxt.setTextColor(Color.parseColor("#18EA46"))
                        binding.resultTxt.text = "건강한상태입"
                    }
                }
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "Frailty" -> {
                val binding = FrailtyResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "SGDSK" -> {
                val binding = SgdskResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "MNA" -> {
                val binding = MnaLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "Nutrition" -> {
                val binding = NutritionLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "Yosil" -> {
                val binding = YosilLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            "NutritionHazard"->{
                val binding = MnaLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
                var traffic = result(type)
                when(traffic) {
                    1 -> {
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "불량하다"
                        binding.resultTxt.setTextColor(Color.parseColor("#EE3B3B"))
                    }
                    2 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "문제가있을수있다"
                        binding.resultTxt.setTextColor(Color.parseColor("#FECA13"))
                    }
                    3 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                        binding.resultTxt.setTextColor(Color.parseColor("#18EA46"))
                        binding.resultTxt.text = "좋다"
                    }
                }
                binding.nextstage.setOnClickListener {
                    var intent = Intent(this, QuestionSelect::class.java)
                    startActivity(intent)
                }
                binding.gotoEdu.setOnClickListener {
                    var intent = Intent(this, CardActivity::class.java)
                    startActivity(intent)
                }
                binding.complete.setOnClickListener {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } //완료
        }
    }

    fun result(type: String) : Int {
        Log.d("test","${type}")
        var ans : Int=0
        for (i in 0 until answer.size) {
            when (type) {
                "EQ5D" -> {
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
                    weight = 1 - (0.096 + weight + 0.078 * flag)
                    if(weight in 0.73..0.904){
                        ans=3
                    }
                    else if(weight in 0.343 .. 0.73){
                        ans=2
                    }
                    else{
                        ans=1
                    }
                } //완료
                "IPAQ" ->
                    when (i) {

                    }
                "MNA" ->
                    when (i) {

                    }
                "SleepHabit" ->
                    when (i) {

                    }
                "Fall"->
                    when(i){

                    }
                "MouthHealth"-> {
                    when (answer[i].toInt()) {
                        1 -> weight+=1
                        2 -> weight+=2
                        3 -> weight+=3
                        4 -> weight+=4
                        5 -> weight+=5
                    }
                    when (weight.toInt()) {
                        in 8..16 -> ans = 3
                        in 17..32 -> ans = 2
                        else -> ans = 1
                    }
                    Log.d("test","구강건강 : $weight")
                }
                "Frailty"->
                    when(i){

                    }
                "Nutrition"->
                    when(i){

                    }
                "SGDSK"->
                    when(i){

                    }
                "Yosil"->
                    when(i){

                    }
                "NutritionHazard"->{
                    when(answer[i].toInt()){
                        1-> weight=weight+1
                    }
                    when(weight.toInt()) {
                        in 0..2 -> ans = 3
                        in 3..4 -> ans = 2
                        else -> ans = 1
                    }
                } //완료
            }
        }
        return ans
    }
}