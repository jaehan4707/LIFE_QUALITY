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
import com.example.myapplication.databinding.SgdskResultBinding
import com.example.myapplication.databinding.SleephabitResultBinding
import com.example.myapplication.databinding.YosilLayoutBinding
import com.example.myapplication.question.QuestionSelect

class ResultLayout : AppCompatActivity() {

    var weight: Double = 0.0
    var flag: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //여기서 점수를 쫙 계산해야함.
        Log.d("test", "설문응답 : ${answer}")
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
                var traffic = result(type) //resutl IPAQ는 MET를 구함.
                when(traffic){
                    1->{
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text="낮은 수준"
                        binding.resultTxt.setTextColor(Color.parseColor("#EE3B3B"))
                    }
                    2->{
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text="중간 수준"
                        binding.resultTxt.setTextColor(Color.parseColor("#FECA13"))
                    }
                    3->{
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                        binding.resultTxt.setTextColor(Color.parseColor("#18EA46"))
                        binding.resultTxt.text="높은 수준"
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
            "MouthHealth" -> {
                val binding = MouthhealthResultBinding.inflate(layoutInflater)
                setContentView(binding.root)
                var traffic = result(type)
                when(traffic) {
                    1 -> {
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "나쁨"
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
                        binding.resultTxt.text = "건강한상태"
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
                var traffic = result(type)
                when(traffic) {
                    1 -> {
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "심한 우울증"
                        binding.resultTxt.setTextColor(Color.parseColor("#EE3B3B"))
                    }
                    2 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "가벼운 우울증"
                        binding.resultTxt.setTextColor(Color.parseColor("#FECA13"))
                    }
                    3 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                        binding.resultTxt.setTextColor(Color.parseColor("#18EA46"))
                        binding.resultTxt.text = "정상"
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
            }  //완료
            "MNA" -> {
                val binding = MnaLayoutBinding.inflate(layoutInflater)
                setContentView(binding.root)
                var traffic = result(type)
                when(traffic) {
                    1 -> {
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "영양 불량 상태"
                        binding.resultTxt.setTextColor(Color.parseColor("#EE3B3B"))
                    }
                    2 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "영양 불량 위험 상태"
                        binding.resultTxt.setTextColor(Color.parseColor("#FECA13"))
                    }
                    3 -> {
                        binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                        binding.resultTxt.setTextColor(Color.parseColor("#18EA46"))
                        binding.resultTxt.text = "정상"
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
                var traffic = result(type)
                when(traffic) {
                    1 -> {
                        binding.redLight.setBackgroundResource(R.drawable.red_circle)
                        binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                        binding.resultTxt.text = "위험입"
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
            } //완료
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
                        0 -> weight += answer[i].toInt() //격렬한 활동 횟수
                        1 -> weight = weight * answer[i].toInt() * 8 //격렬한 활동 시간 *가중치
                        2 -> flag = answer[i].toInt() //중간횟수
                        3 -> weight += flag * answer[i].toInt() * 4 //중간 활동 시간 * 가중치
                        4 -> flag = answer[i].toInt()//가벼운 활동 횟수
                        5 -> weight += flag * answer[i] * 3.3 //가벼운 활동 시간 * 가중치
                        6 -> weight += answer[i] * 3.3 //앉아서 보내느 시간 * 가중치
                    }// } //weight 는 MET -> 이제 여기서 판단을 해야함. 완료
                "MNA" -> {
                    when(i){
                        5 -> {
                            when(answer[i]){
                                in 0 until 19->{
                                    weight+=0
                                }
                                in 19 until 21->{
                                    weight+=1
                                }
                                in 21 until  23->{
                                    weight+=2
                                }
                                else -> weight+=3
                            }
                        }
                        else ->{
                            weight+=answer[i]
                        }
                    }
                } //완료
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

                } //완료
                "Frailty"->
                    when(i){

                    }
                "Nutrition"->
                    when(i){

                    }
                "SGDSK"-> {
                    when (answer[i]) {
                        1 -> weight += 1
                    }
                } //완료
                "Yosil"-> {
                    when (answer[i].toInt()) {
                        1 -> weight += 1
                        2 -> weight += 2
                        3 -> weight += 3
                        4 -> weight += 4
                        5 -> weight += 5
                    }

                } //완료
                "NutritionHazard"->{
                    when(answer[i].toInt()){
                        1-> weight=weight+1
                    }

                } //완료
            }
        }
        if(type=="MouthHelath"){
            when (weight.toInt()) {
                in 7..14 -> ans = 3
                in 15..28 -> ans = 2
                else -> ans = 1
            }
            Log.d("test","구강건강 : $weight")
        }
        else if(type=="IPAQ"){
            Log.d("test","MET : ${weight}")
            ans = if((answer[0]>=3 && weight >=1500)||(weight >= 3000)){
                3 //격렬하다
            } else if((answer[0]>=3 && answer[1]>=20)||
                (answer[2]>=5 && answer[3]>=30)||
                (weight>=600)) {
                2 //중간상태
            } else {
                1 //낮은 상태
            }
        }
        else if(type=="SGDSK"){
            when(weight.toInt()){
                in 0 .. 5 -> ans=3
                in 6 ..9 -> ans=2
                else -> ans=1
            }
        }
        else if(type=="NutritionHazard"){
            when(weight.toInt()){
                in 0..2 -> ans = 3
                in 3..4 -> ans = 2
                else -> ans = 1
            }
        }
        else if(type=="Yosil"){
            when (weight.toInt()) {
                in 8..16 -> ans = 3
                in 17..32 -> ans = 2
                else -> ans = 1
            }
        }
        else if(type =="MNA"){
            when(weight.toInt()){
                in 12 .. 14 -> ans=3
                in 8 .. 11 -> ans=2
                in 0 .. 7 -> ans=1
            }
        }
        return ans
    }
}