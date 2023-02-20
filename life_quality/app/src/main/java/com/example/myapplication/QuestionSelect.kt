package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.example.myapplication.MainActivity.Companion.Total
import com.example.myapplication.MainActivity.Companion.surveyList
import com.example.myapplication.MainActivity.Companion.type
import com.example.myapplication.TotalSurvey
import com.example.myapplication.databinding.ActivityQuestionSelectBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class QuestionSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityScope = CoroutineScope(Dispatchers.Main)
        var binding = ActivityQuestionSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        var tes = mutableListOf<TotalSurvey>()
        var nameList = mutableListOf<String>(
            "EQ5D",
            "Fall",
            "Frailty",
            "IPAQ",
            "MNA",
            "MouthHealth",
            "SGDSK",
            "SleepHabit",
            "Yosil",
            "Nutrition",
            "NutritionHazard",
            "SDoH"
        )
        var sdohList = mutableListOf<String>("Drink", "SocialNetWork", "Smoke", "IPAQ")
        binding.selectStart.setOnClickListener { //설문 시작하기 버튼 눌렀을 때
            Toast.makeText(this@QuestionSelect, "설문시작하기 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show()
            binding.selectStart.isSelected = !binding.selectStart.isSelected
            if (binding.selectStart.isSelected) {
                var checkRadio: Int = binding.group.checkedRadioButtonId
                if (checkRadio == -1) {
                    //만약 라디오버튼을 선택하지 않은 채 시작하기 버튼을 눌렀다면 진행하면 안됨.
                    Toast.makeText(this, "설문목록을 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("test","Radio : ${checkRadio}")
                    var btn = binding.group.findViewById<RadioButton>(checkRadio)
                    var dbid: Int = 0
                    when (btn.text.toString()) {
                        "삶의 질" -> dbid = 0 //EQ5D
                        "수면습관" -> dbid = 7 //Sleep
                        "정신건강" -> dbid = 6 //SGDSK
                        "구강건강" -> dbid = 5 //Mouth
                        "영양상태 측정(MNA)" -> dbid = 4 //MNA -> NutritionHazard로 바꿈.
                        "신체활동설문(IPAQ)" -> dbid = 3 //IPAQ
                        "낙상" -> dbid = 1
                        "노쇠측정" -> dbid = 2
                        "요실금" -> dbid = 8
                        "식습관" -> dbid = 10
                        "SDoH" -> dbid = 11
                    }
                    surveyList.clear()
                    type = nameList[dbid]
                    Log.d(
                        "test",
                        "${btn.text.toString()}" + "${nameList[dbid]}" + "  dbid = " + dbid
                    )
                    //카테고리 선택하면 카테고리별로 디비뽑음.
                    runBlocking {
                        val job = CoroutineScope(Dispatchers.IO).launch {
                            if (dbid == 11) {
                                for (j in 0 until sdohList.size) {
                                    for (i in 0 until Total.size) {
                                        if (Total[i].surveyType == sdohList[j]) {
                                            surveyList.add(Total[i])
                                        }
                                    }
                                }
                            } else {
                                for (i in 0 until Total.size) {
                                    if (Total[i].surveyType == nameList[dbid]) {
                                        surveyList.add(Total[i])
                                    }
                                }
                            }
                        }
                        job.join() //job이 끝날떄까지 대기함.
                        var intent = Intent(this@QuestionSelect, QuestionMainpage::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
        //해당 버튼의 해당하는 설문리스트를 불러와야함.
        binding.selectClear.setOnClickListener{

        }
    }
}