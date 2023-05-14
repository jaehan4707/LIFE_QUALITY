package com.example.myapplication.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.MainActivity.Companion.Socialnum
import com.example.myapplication.MainActivity.Companion.Total
import com.example.myapplication.MainActivity.Companion.check_list
import com.example.myapplication.MainActivity.Companion.drinknum
import com.example.myapplication.MainActivity.Companion.smokenum
import com.example.myapplication.MainActivity.Companion.surveyList
import com.example.myapplication.MainActivity.Companion.type
import com.example.myapplication.R
import com.example.myapplication.TotalSurvey
import com.example.myapplication.databinding.ActivityQuestionSelectBinding
import com.example.myapplication.question.QuestionMainpage.Companion.dbid
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class QuestionSelect : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionSelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityScope = CoroutineScope(Dispatchers.Main)
        var binding = ActivityQuestionSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        var tes = mutableListOf<TotalSurvey>()
        var nameList = mutableListOf<String>(
            /*
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
            //"NutritionHazard",
            "SDoH"
             */
            "EQ5D","SGDSK","SleepHabit","Fall","MouthHealth","Frailty", "IPAQ", "MNA", "Yosil", "Nutrition","SDoH"
            //"NutritionHazard",
        )
        for(i in 0 until check_list.size){
            if(check_list[i]){ //이미 완료된 설문이라면
                when(i){
                    0 -> binding.rb1.isEnabled=false
                    1-> binding.rb2.isEnabled=false
                    2 -> binding.rb3.isEnabled=false
                    3-> binding.rb4.isEnabled=false
                    4 -> binding.rb5.isEnabled=false
                    5-> binding.rb6.isEnabled=false
                    6 -> binding.rb7.isEnabled=false
                    7-> binding.rb8.isEnabled=false
                    8 -> binding.rb9.isEnabled=false
                    9-> binding.rb10.isEnabled=false
                    10->binding.rb11.isEnabled=false
                }
            }
        }
        var skip_List = mutableListOf<String>("Drink", "SocialNetWork", "Smoke", "IPAQ")
        var sdoh_List = mutableListOf<String>("Drink", "SocialNetWork", "Smoke")
        binding.selectStart.setOnClickListener { //설문 시작하기 버튼 눌렀을 때
            binding.selectStart.isSelected = !binding.selectStart.isSelected
            if (binding.selectStart.isSelected) { //설문 시작을 눌렀을대.?
                var checkRadio: Int = binding.group.checkedRadioButtonId
                if (checkRadio == -1) {
                    //만약 라디오버튼을 선택하지 않은 채 시작하기 버튼을 눌렀다면 진행하면 안됨.
                    Toast.makeText(this, "설문목록을 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("test","Radio : ${checkRadio}")
                    var btn = binding.group.findViewById<RadioButton>(checkRadio)
                    dbid=0
                    when (btn.text.toString()) {
                        "삶의 질"->dbid = 0
                        "정신건강"->dbid=1
                        "수면습관"->dbid=2
                        "낙상"->dbid=3
                        "구강건강"->dbid=4
                        "노쇠측정"->dbid=5
                        "신체활동설문(IPAQ)"->dbid=6
                        "영양상태 측정(MNA)"->dbid=7
                        "요실금"->dbid=8
                        "식습관"->dbid=9
                        "SDoH"->dbid=10
                    }
                    if(check_list[dbid]){ //설문이 완료된거임.
                        Log.d("test","${nameList[dbid]}는 완료된 설문입니다.")
                        Toast.makeText(this, "이미 완료한 설문목록 입니다", Toast.LENGTH_SHORT).show()
                        //선택을 초기화 해야함.
                        binding.selectStart.isSelected = !binding.selectStart.isSelected

                    }
                    else {
                        Toast.makeText(this@QuestionSelect, "설문시작하기 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show()
                        surveyList.clear()
                        type = nameList[dbid]
                        Log.d(
                            "test",
                            "${btn.text.toString()}" + "${nameList[dbid]}" + "  dbid = " + dbid
                        )
                        //카테고리 선택하면 카테고리별로 디비뽑음.
                        runBlocking {
                            val job = CoroutineScope(Dispatchers.IO).launch {
                                if (dbid == 10) {
                                    for (j in 0 until sdoh_List.size) {
                                        for (i in 0 until Total.size) {
                                            if (Total[i].surveyType == sdoh_List[j]) {
                                                surveyList.add(Total[i])
                                                if (j == 0)
                                                    drinknum++
                                                if (j == 1)
                                                    Socialnum++
                                                if (j == 2)
                                                    smokenum++
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
        }
        //해당 버튼의 해당하는 설문리스트를 불러와야함.
        binding.selectClear.setOnClickListener{

        }
    }
}