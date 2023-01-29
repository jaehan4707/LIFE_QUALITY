package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity.Companion.surveyList
import com.example.myapplication.databinding.QuestionMainpageBinding

class QuestionMainpage: AppCompatActivity() {
    companion object {
        lateinit var tempSurvey : TotalSurvey
        var curNumber : Int = 0
        var allSurvey = surveyList.size + 1 //전체 설문조사 개수 + 인적사항 조사하는 문항 (1 개) 까지 합친 총 개수를 저장
        var curCount = 0
        lateinit var binding : QuestionMainpageBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionMainpageBinding.inflate(layoutInflater)
        setContentView(binding.root);

        for(survey in surveyList) {
            Log.d("surveyList Check", " ${survey.surveyType}, ${survey.answer}")
        }
        if(curCount == 0) {
            setFrag(-1) //디폴트 값으로 일단 인적사항 입력 페이지를 프레그먼트로 보여준다.
        }
        // "다음" 버튼 클릭 이벤트 구현 부분
      binding.nextstage.setOnClickListener() {
            //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
            tempSurvey = surveyList.get(curCount)
            curNumber = curCount + 1
            curCount++;
            if(tempSurvey.type.toInt() == 0) { //답변이 선택형일 경우
                when(tempSurvey.number.toInt()) {
                    //답변 개수에 맞는 프레그먼트를 띄워주는 메소드를 호출한다.
                    2 -> {
                        setFrag(2)
                    }
                    3 -> {
                        setFrag(3)
                    }
                    4 -> {
                        setFrag(4)
                    }
                    5 -> {
                        setFrag(5)
                    }
                    6 -> {
                        setFrag(6)
                    }
                    7 -> {
                        setFrag(7)
                    }
                    8 -> {
                        setFrag(8)
                    }
                    10 -> {
                        setFrag(10)
                    }
                }
            }
            else { //답변이 입력형일 경우
                setFrag(0)
            }

        }

    }
    fun setFrag(fragNum : Int) {
        //프레그먼트를 교체해주는 코딩
        //supportFragmentManager는 프레그먼트를 관리하는 객체임
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum) {
            -1 -> { //설문조사 프레그먼트 뷰를 보여준다
                ft.replace(R.id.main_frame, FragmentInform()).commit()
            }
            0 -> { //입력형 답변일 경우의 프레그먼트를 보여준다.
                ft.replace(R.id.main_frame, NotypeFragment()).commit()
            }
            2 -> {
                //main_frame이라고 id를 준 레이아웃 만큼만 프레그먼트로 보여줄것이고
                //어떤 프레그먼트를 보여줄지 정한다음에 commit() 해주기!
                ft.replace(R.id.main_frame, Fragment2()).commit()
                //프레그먼트를 띄워줬으면 안에 내용을 채워야겠지?

            }
            3 -> {
                ft.replace(R.id.main_frame, Fragment3()).commit()
            }
            4 -> {
                ft.replace(R.id.main_frame, Fragment4()).commit()
            }
            5 -> {
                ft.replace(R.id.main_frame, Fragment5()).commit()
            }
            6 -> {
                ft.replace(R.id.main_frame, Fragment6()).commit()
            }
            7 -> {
                ft.replace(R.id.main_frame, Fragment7()).commit()
            }
            8 -> {
                ft.replace(R.id.main_frame, Fragment8()).commit()
            }
            10 -> {
                ft.replace(R.id.main_frame, Fragment10()).commit()
            }
        }
    }
}