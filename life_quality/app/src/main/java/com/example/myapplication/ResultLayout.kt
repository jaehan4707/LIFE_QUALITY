package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity.Companion.answer
import com.example.myapplication.MainActivity.Companion.check_list
import com.example.myapplication.MainActivity.Companion.dbid
import com.example.myapplication.MainActivity.Companion.ipaq_list
import com.example.myapplication.MainActivity.Companion.type
import com.example.myapplication.SplashActivity.Companion.token
import com.example.myapplication.SplashActivity.Companion.user
import com.example.myapplication.databinding.*
import com.example.myapplication.question.QuestionSelect
import com.example.myapplication.result.Eq5dFragment
import com.example.myapplication.result.FallFragment
import com.example.myapplication.result.*
import com.google.android.gms.tasks.Task
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.QuerySnapshot

class ResultLayout : AppCompatActivity() {

    val binding: ResultLayoutBinding by lazy {
        ResultLayoutBinding.inflate(layoutInflater)
    }
    companion object {
        var traffic : Int =0
        var weight : Double=0.0
        var flag: Int = 0
    }
    val tasks = mutableListOf<Task<QuerySnapshot>>()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val record_date =SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
    val date = record_date.format(Date())
    // 설문 조사 완료 일시를 날짜 형식으로 변환한다
    private val surveyCompletionTime = System.currentTimeMillis()
    private val formattedCompletionTime = dateFormat.format(Date(surveyCompletionTime))

    //val sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000
    //val triggerTime = surveyCompletionTime + sevenDaysInMillis //설문 완료 이후 7일 이후의 시간
    private val triggerTime = System.currentTimeMillis() + 30_000 // 현재 시간에서 30초(30,000 밀리초)를 더한 값 실제 테스트에서는 7일로 바꿔야함.
    private val triggerFormat = dateFormat.format(Date(triggerTime))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //여기서 점수를 쫙 계산해야함.
        setContentView(binding.root)
        Log.d("test", "설문응답 : ${answer}, dbid : ${dbid}")
        Log.d("problem","설문조사 완료 시간 : $formattedCompletionTime")
        val database = FirebaseDatabase.getInstance()
        val answerRef = database.getReference("User/token/${token!!}/${date}/${type}/answer")
        answerRef.setValue(answer).addOnSuccessListener {
            Log.d("problem", "answer 저장 성공")
        }
            .addOnFailureListener { exception ->
                Log.d("problem", "answer 저장 실패", exception)
            }
        setPush() //알림보내기.
        weight = 0.0
        flag = 0
        check_list[dbid]=true
        Log.d("problem","type : ${type}")

        traffic= result(type) //결과값구하기.

        when (type) {
            "EQ5D" -> {
                Log.d("problem","EQ5D")
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    Eq5dFragment()
                ).commit()
                true
                /*
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
                 */
            } //완료
            "Fall" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    FallFragment()
                ).commit()
                /*
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

                 */
            }
            "SleepHabit" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    SleepFragment()
                ).commit()
                /*
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

                 */
            }
            "IPAQ" -> {
                ipaq_list=answer
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    IpaqFragment()
                ).commit()
                /*
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

                 */
            } //완료
            "MouthHealth" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    MouthHealthFragment()
                ).commit()
                /*
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
                */
            } //완료
            "Frailty" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    FrailtyFragment()
                ).commit()
                /*
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

                 */
            }
            "SGDSK" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    SgdskFragment()
                ).commit()
                /*
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
                 */
            }  //완료
            "MNA" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    MnaFragment()
                ).commit()
                /*
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
                        check_list[9]=true //영양 고득점시 식습관 설문조사를 시행하지 않도록 만듬.
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
                 */
            } //완료
            "Yosil" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    YosilFragment()
                ).commit()
                /*
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
                 */
            } //완료
            "Sdoh"->{
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    SdohFragment()
                ).commit()
            }
            else->false
        }

        binding.nextstage.setOnClickListener { //계속하기 버튼
            var intent = Intent(this, QuestionSelect::class.java)
            startActivity(intent)
        }
        binding.goToEdu.setOnClickListener { //완료하기 버튼
            var intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }

    }
    private fun setPush(){
        Log.d("problem","알람 시간 : ${triggerFormat}")
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notificationIntent = Intent(this,MyNotificationReceiver::class.java)
        notificationIntent.putExtra("content","검사를 안한지 7일이나 지났어요!!")
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        )
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
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
                        /*
                        0 -> weight += answer[i].toInt() //격렬한 활동 횟수
                        1 -> weight = weight * answer[i].toInt() * 8 //격렬한 활동 시간 *가중치
                        2 -> flag = answer[i].toInt() //중간횟수
                        3 -> weight += flag * answer[i].toInt() * 4 //중간 활동 시간 * 가중치
                        4 -> flag = answer[i].toInt()//가벼운 활동 횟수
                        5 -> weight += flag * answer[i] * 3.3 //가벼운 활동 시간 * 가중치
                        6 -> weight += answer[i] * 3.3 //앉아서 보내느 시간 * 가중치
                         */
                        2->weight+=answer[i]
                        4->weight+=answer[i]
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
                "Fall"->weight+=answer[i]
                "MouthHealth"-> weight+=answer[i]
                "Frailty"->
                    when(i){
                        2->{
                            //격렬한 활동은 list[0] * list[1] //0은 일수, 1은 분
                            var Met : Double=0.0
                            Met += (ipaq_list[0]* ipaq_list[1] *8 + ipaq_list[2]* ipaq_list[3]*4+ipaq_list[4]* ipaq_list[5]*3.3+ipaq_list[6])*0.0035 * answer[i] * 5
                            //해당 칼로리.
                            if(user.Sex=="남성"){
                                if(Met<495)
                                    weight+=1
                            }
                            else if(user.Sex=="여성"){
                                if(Met<284)
                                    weight+=1
                            }
                        }
                        else->weight+=answer[i]
                    }
                "SGDSK"-> { weight+=answer[i]
                    /*
                    when (answer[i]) {
                        1 -> weight += 1
                    }
                     */
                } //완료
                "Yosil"-> weight+=answer[i] //완료
            }
        }
        return ans
    }
}