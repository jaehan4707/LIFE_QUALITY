package com.example.myapplication.question

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.CardActivity
import com.example.myapplication.EduActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.SplashActivity.Companion.Total
import com.example.myapplication.SplashActivity.Companion.check_list
import com.example.myapplication.R
import com.example.myapplication.SplashActivity
import com.example.myapplication.SplashActivity.Companion.dbid
import com.example.myapplication.SplashActivity.Companion.nameList
import com.example.myapplication.SplashActivity.Companion.surveyList
import com.example.myapplication.SplashActivity.Companion.type
import com.example.myapplication.TotalSurvey
import com.example.myapplication.databinding.ActivityQuestionSelectBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*


class QuestionSelect : AppCompatActivity() {
    //private lateinit var binding: ActivityQuestionSelectBinding
    private var check_id=-1
    private lateinit var radio_text : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =ActivityQuestionSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        for(i in 0 until check_list.size){
            if(check_list[i]){ //이미 완료된 설문이라면
                when(i){
                    0 -> {
                        binding.rb1.isEnabled=false
                        binding.rb1.setBackgroundResource(R.drawable.x_box)
                    }
                    1-> {
                        binding.rb2.isEnabled=false
                        binding.rb2.setBackgroundResource(R.drawable.x_box)
                    }
                    2 -> {
                        binding.rb3.isEnabled = false
                        binding.rb3.setBackgroundResource(R.drawable.x_box)
                    }
                    3-> {
                        binding.rb4.isEnabled=false
                        binding.rb4.setBackgroundResource(R.drawable.x_box)
                    }
                    4 -> {
                        binding.rb5.isEnabled=false
                        binding.rb5.setBackgroundResource(R.drawable.x_box)
                    }
                    5-> {
                        binding.rb6.isEnabled=false
                        binding.rb6.setBackgroundResource(R.drawable.x_box)
                    }
                    6 -> {
                        binding.rb7.isEnabled=false
                        binding.rb7.setBackgroundResource(R.drawable.x_box)
                    }
                    7-> {
                        binding.rb8.isEnabled=false
                        binding.rb8.setBackgroundResource(R.drawable.x_box)
                    }
                    8 -> {
                        binding.rb9.isEnabled=false
                        binding.rb9.setBackgroundResource(R.drawable.x_box)
                    }
                    9-> {
                        binding.rb10.isEnabled=false
                        binding.rb10.setBackgroundResource(R.drawable.x_box)
                    }
                }
            }
        }

        val radioGroup = findViewById<RadioGroup>(R.id.group)

        radioGroup.setOnCheckedChangeListener { group, checkedId -> // 선택된 RadioButton의 ID를 확인하여 처리합니다.
            when (checkedId) {
                R.id.rb1 ->dbid=0 //mna
                R.id.rb2->dbid=1 //정신건강
                R.id.rb3->dbid=2 //수면습관
                R.id.rb4->dbid=3 //구간건강
                R.id.rb5->dbid=4 //
                R.id.rb6->dbid=5
                R.id.rb7->dbid=6
                R.id.rb8->dbid=7
                R.id.rb9->dbid=8
                R.id.rb10->dbid=9
            }
        }
        binding.selectStart.setOnClickListener { //설문 시작하기 버튼 눌렀을 때
            binding.selectStart.isSelected = !binding.selectStart.isSelected
            if (binding.selectStart.isSelected) { //설문 시작을 눌렀을대.?
                var checkRadio: Int = binding.group.checkedRadioButtonId
                if (checkRadio == -1) {
                    //만약 라디오버튼을 선택하지 않은 채 시작하기 버튼을 눌렀다면 진행하면 안됨.
                    Toast.makeText(this, "설문목록을 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    if(check_list[dbid]){ //설문이 완료된거임.
                        Log.d("test","${nameList[dbid]}는 완료된 설문입니다.")
                        Toast.makeText(this, "이미 완료한 설문목록 입니다", Toast.LENGTH_SHORT).show()
                        //선택을 초기화 해야함.
                        binding.selectStart.isSelected = !binding.selectStart.isSelected
                    }
                    else if(!check_list[4] && dbid==6){
                        Toast.makeText(this,"노쇠측정을 하기 전에 IPAQ 설문을 진행해주셔야 합니다!",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Log.d("problem","클릭클릭")
                        Toast.makeText(this@QuestionSelect, "설문시작하기 버튼을 눌렀습니다", Toast.LENGTH_SHORT).show()
                        surveyList.clear()
                        type = nameList[dbid]
                        Log.d("problem","${type}")
                        Log.d("problem", "Total : ${Total.size}")
                        //카테고리 선택하면 카테고리별로 디비뽑음.
                        CoroutineScope(Dispatchers.Main).launch {
                            val filteredSurveyList = withContext(Dispatchers.IO) {
                                val tempList = mutableListOf<TotalSurvey>()
                                for (i in 0 until Total.size) {
                                    if (SplashActivity.Total[i].surveyType == nameList[dbid]) {
                                        tempList.add(Total[i])
                                    }
                                }
                                tempList
                            }
                            surveyList.addAll(filteredSurveyList)
                            Log.d("problem", "작업 : ${surveyList.size}")
                            val intent = Intent(this@QuestionSelect, QuestionMainpage::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
        //해당 버튼의 해당하는 설문리스트를 불러와야함.
        binding.selectClear.setOnClickListener{
                val intent = Intent(this@QuestionSelect, EduActivity::class.java)
                startActivity(intent)
        }
    }
    fun survey_clear() : Boolean{
        for(i in 0 ..4){
            if(!check_list[i])
                return false
        }
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this@QuestionSelect,MainActivity::class.java)
        startActivity(intent)
    }
}