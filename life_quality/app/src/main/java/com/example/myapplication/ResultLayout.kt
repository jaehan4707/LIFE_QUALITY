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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LoginActivity.Companion.phone
import com.example.myapplication.LoginActivity.Companion.userCollectionRef
import com.example.myapplication.SplashActivity.Companion.answer
import com.example.myapplication.SplashActivity.Companion.check_list
import com.example.myapplication.SplashActivity.Companion.dbid
import com.example.myapplication.SplashActivity.Companion.token
import com.example.myapplication.SplashActivity.Companion.type
import com.example.myapplication.SplashActivity.Companion.user
import com.example.myapplication.databinding.*
import com.example.myapplication.model.User
import com.example.myapplication.question.QuestionSelect
import com.example.myapplication.result.Eq5dFragment
import com.example.myapplication.result.FallFragment
import com.example.myapplication.result.*
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

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
    lateinit var ipaq_result : ArrayList<Double>
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val record_date =SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
    val date = record_date.format(Date()) // 설문 조사 완료 일시를 날짜 형식으로 변환한다
    private val surveyCompletionTime = System.currentTimeMillis()
    private val formattedCompletionTime = dateFormat.format(Date(surveyCompletionTime))
    val sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000
    private val triggerTime = System.currentTimeMillis() + sevenDaysInMillis // 현재 시간에서 30초(30,000 밀리초)를 더한 값 실제 테스트에서는 7일로 바꿔야함.
    private val triggerFormat = dateFormat.format(Date(triggerTime))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //여기서 점수를 쫙 계산해야함.
        setContentView(binding.root)
        Log.d("test", "설문응답 : ${answer}, dbid : ${dbid}")
        Log.d("problem","설문조사 완료 시간 : $formattedCompletionTime")

        val userDocRef = userCollectionRef.document(phone) //phone 경로
        val newDateDocument = userDocRef.collection("Result").document(date.toString())
        val answerData = hashMapOf(
            "${type}" to answer, // 필요한 다른 필드들도 추가
        )
        newDateDocument.get().addOnSuccessListener { document ->
            if (document.exists()) {
                // 해당 날짜의 문서가 이미 존재하는 경우
                newDateDocument.update(answerData as Map<String, Any>)
                    .addOnSuccessListener {
                        Log.d("problem", "업데이트 성공!!!")
                        Toast.makeText(this, "날짜 문서를 업데이트했습니다.", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Log.d("problem", "업데이트 실패!!")
                    }
            } else {
                // 해당 날짜의 문서가 존재하지 않는 경우
                newDateDocument.set(answerData as Map<String, Any>)
                    .addOnSuccessListener {
                        Log.d("problem", "추가 성공!!!")
                        Toast.makeText(this, "날짜 문서를 추가했습니다.", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Log.d("problem", "추가 실패!!")
                    }
            }
        }
        val database = FirebaseDatabase.getInstance()
        val answerRef = database.getReference("User/phone/$phone/$date/$type")
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
        if (type == "Frailty") {
            val surveyRef = database.getReference("User/phone/${phone!!}/${date}/IPAQ")
            val result = ArrayList<Double>()
            surveyRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (userSnapshot in dataSnapshot.children) {
                        val value = userSnapshot.getValue(Double::class.java)
                        value?.let {
                            result.add(it)
                        }
                    }
                    Log.d("problem", "ipaq_result: $result")
                    ipaq_result=result
                    // ipaq_result에 저장된 값을 사용합니다.
                    traffic=result(type)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    Log.d("problem", "데이터베이스 읽기 작업이 취소되었습니다.", databaseError.toException())
                }
            })
        }
        when (type) {
            "EQ5D" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    Eq5dFragment()
                ).commit()
                true
            } //완료
            "Fall" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    FallFragment()
                ).commit()
            }
            "SleepHabit" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    SleepFragment()
                ).commit()
            }
            "IPAQ" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    IpaqFragment()
                ).commit()
            } //완료
            "MouthHealth" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    MouthHealthFragment()
                ).commit()
            }
            "SGDSK" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    SgdskFragment()
                ).commit()

            }  //완료
            "MNA" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    MnaFragment()
                ).commit()
            } //완료
            "Yosil" -> {
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    YosilFragment()
                ).commit()
            } //완료
            "SocialNetwork"->{
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    SdohFragment()
                ).commit()
            }
            "Frailty"->{
                supportFragmentManager.beginTransaction().replace(binding.resultFrame.id,
                    FrailtyFragment()
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
        if(type!="Frailty"){
            traffic=result(type)
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
        Log.d("test", "${type}")
        var ans: Int = 0
        if (type == "SleepHabit") { //13문항이 바꼇음 따라서 13 이후로는 +1해야함.
            var first = answer[14] //#6
            var second = when(answer[1].toInt()){ //#2+5a
                in 0 .. 15 -> 0+answer[4]
                in 16 .. 30-> 1+answer[4]
                in 31 .. 60->2+answer[4]
                else -> 3+answer[4]
            }
            var third = when(answer[3].toInt()){ //#4
                in 0 until 5 -> 3
                in 5 until 6 ->2
                in 6 until 7 ->1
                else ->0
            }
            var four = when( (answer[3]/(answer[2]-answer[0])*100).toInt()){
                in 0 until 65 ->3
                in 65 .. 74 ->2
                in 75 .. 84 ->1
                else ->0
            }
            var n = 0.0
            for(i in 5 .. 13){ //#5b~5j
                n+=answer[i]
            }
            var five = when(n.toInt()){
                0->0
                in 1..9 ->1
                in 10 ..18 ->2
                else ->3
            }
            var six = answer[15]
            var seven = when((answer[16]+answer[17]).toInt()){
                0->0
                in 1..2 -> 1
                in 3..4 ->2
                else ->3
            }
            weight = first+second+third+four+five+six+seven
        } else {
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
                        if (weight in 0.73..0.904) {
                            ans = 3
                        } else if (weight in 0.343..0.73) {
                            ans = 2
                        } else {
                            ans = 1
                        }
                    } //완료
                    "IPAQ" -> {
                        when (i) {
                            1 -> weight += answer[i]*answer[i-1]
                            3 -> weight += answer[i]*answer[i-1]
                        }
                    }// } //weight 는 MET -> 이제 여기서 판단을 해야함. 완료
                    "MNA" -> {
                        when (i) {
                            5 -> {
                                weight += when (answer[i].toInt()) {
                                    in 0 until 19 -> 0
                                    in 19 until 21 -> 1
                                    in 21 until 23 -> 2
                                    else -> 3
                                }
                            }

                            else -> {
                                weight += answer[i]
                            }
                        }
                        if (weight < 17.0) //영양 불량
                            ans = 1
                        else if (weight in 17.0..23.5) //영양불량 위험
                            ans = 2
                        else //정상
                            ans = 3
                    } //완료
                    "Fall" -> weight += answer[i]
                    "MouthHealth" -> weight += answer[i]
                    "Frailty" -> {
                        Log.d("problem","노쇠측정 결과 계산")
                        when (i) {
                            2 -> {
                                //격렬한 활동은 list[0] * list[1] //0은 일수, 1은 분
                                var Met: Double = 0.0
                                Log.d("problem","list : ${ipaq_result}")
                                Met += (ipaq_result[0] *ipaq_result[1] * 8 + ipaq_result[2] * ipaq_result[3]* 4 + ipaq_result[4] * ipaq_result[5] * 3.3 + ipaq_result[6]) * 0.0035 * answer[i] * 5
                                //해당 칼로리.
                                if (user.Sex == "남성") {
                                    if (Met < 495)
                                        weight += 1
                                } else if (user.Sex == "여성") {
                                    if (Met < 284)
                                        weight += 1
                                }
                            }
                            else -> weight += answer[i]
                        }
                    }

                    "SGDSK" -> weight += answer[i]
                    "Yosil" -> weight += answer[i]
                }
            }
        }
        return ans
    }
}