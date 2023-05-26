package com.example.myapplication.question

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.*
import com.example.myapplication.MainActivity.Companion.answer
import com.example.myapplication.MainActivity.Companion.check_list
import com.example.myapplication.MainActivity.Companion.dbid
import com.example.myapplication.MainActivity.Companion.surveyList
import com.example.myapplication.databinding.QuestionMainpageBinding

class QuestionMainpage : AppCompatActivity() {
    companion object {
        lateinit var tempSurvey: TotalSurvey
        lateinit var group: RadioGroup
        //var keyList = mutableListOf<String>()
        //var valueList = mutableListOf<String>()
        var Id: Int = -1
        var curCount = 0
        var flag = false

        lateinit var binding: QuestionMainpageBinding
    }
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //설문을 새로 시작할 때마다 초기화 해주어야 한다.
        curCount = 1
        binding = QuestionMainpageBinding.inflate(layoutInflater)
        setContentView(binding.root);
        var page = surveyList.get(0).number.toInt()
        Log.d("problem", "fragment : ${page}")

        //프로그레스바 max값 정해주는 부분 (max는 설문의 개수만큼 되어야 한다.)
        binding.progressbar.max = surveyList.size
        tempSurvey = surveyList.get(0)
        Log.d("problem"," tempsurvey : ${tempSurvey.type}")
        answer.clear() //정답 배열 초기화.
        setFrag(page)
        //인적사항 입력할 때는 진행도가 1임
        binding.progressbar.progress = 0

        // "다음" 버튼 클릭 이벤트 구현 부분
        binding.backStage.setOnClickListener{
            onBackBtnPressed()
        }
        binding.nextstage.setOnClickListener() {
            //설문이 끝났을 경우 결과 확인 페이지로 이동
            Log.d("test", "라디오 버튼의 값 : $Id")
            Log.d("test", "${tempSurvey.type}")
            Log.d("test", "조사타입 : ${tempSurvey.surveyType}")
            when(tempSurvey.surveyType){
                "IPAQ" -> {
                    if (Id == 0 && tempSurvey.type == "0") { //다음 화면 진행하지 않고 jump 해야함.
                        Log.d("test", "다음 화면을 진행하지 않습니다")
                        answer.add(Id)
                        answer.add(Id)
                        curCount++
                        Id = -1
                        binding.progressbar.progress++
                    } else { //화며 skip 없을때
                        answer.add(Id)
                        Id = -1
                    }
                    if (curCount == surveyList.size) {
                        var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                        startActivity(intent)
                    } else {
                        //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                        tempSurvey = surveyList.get(curCount)
                        curCount++;
                        //마지막 문항일 경우에는 다음 버튼이 "결과보기"로 변경되어야 함
                        if (curCount == surveyList.size) {
                            binding.nextstage.text = "결과보기"
                        }
                        Log.d("curCount List", "curCount = $curCount")

                        if (tempSurvey.type.toInt() == 0) { //답변이 선택형일 경우
                            setFrag(tempSurvey.number.toInt())
                        } else { //답변이 입력형일 경우
                            setFrag(0)
                        }
                    }
                    binding.progressbar.progress++
                }
                else -> { //IPAQ
                    if (Id == -1) {
                        Toast.makeText(this, "설문지를 선택하지 않았습니다!!", Toast.LENGTH_SHORT).show()
                    }
                    else if (Id != -1) {
                        answer.add(Id)
                        Id = -1
                        Log.d("test", "answer : ${answer[curCount - 1]}")
                        Log.d("test","curCount : $curCount")
                        Log.d("test","answer : ${answer}")
                        if (curCount == surveyList.size) {
                            Log.d("test","dbid : ${dbid }")
                            var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                            startActivity(intent)
                        } else {
                            //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                            tempSurvey = surveyList.get(curCount)
                            curCount++;
                            //마지막 문항일 경우에는 다음 버튼이 "결과보기"로 변경되어야 함
                            if (curCount == surveyList.size) {
                                binding.nextstage.text = "결과보기"
                            }
                            Log.d("curCount List", "curCount = $curCount")

                            if (tempSurvey.type.toInt() == 0 || tempSurvey.type.toInt() == 5 || tempSurvey.type.toInt()==6) { //답변이 선택형일 경우
                                setFrag(tempSurvey.number.toInt())
                            } else { //답변이 입력형일 경우
                                setFrag(0)
                            }
                        }
                        binding.progressbar.progress++
                    }
                }
            }
        }
    }
    fun onBackBtnPressed() {
        Log.d("test","뒤로가기를 눌렀습니다")
        if (currentFragment is FragmentInform) {
            // FragmentInform에서 뒤로가기 버튼을 눌렀을 때 액티비티를 종료
           Log.d("test","잘못된 뒤로가기")
            super.onBackPressed()
        } else {
            Log.d("test","이전프래그먼트로 돌아갑니다, 문항 : ${curCount}을 지웁니다.")
            //뒤로가기하면 이전 선택지에 대한 응답을 지워야합니다. 그쵸?
            curCount--
            Log.d("test","before tempsurvey: ${surveyList[curCount].title}")
            tempSurvey=surveyList[curCount-1]
            binding.progressbar.progress--
            Log.d("test", "지우고 나기 전 : ${answer}")
            answer.removeAt(answer.size-1)
            Log.d("test","지우고 난 후 :${answer}")

            Log.d("test","after tempsurvey: ${surveyList[curCount-1].title}")
            if (surveyList[curCount].type.toInt() == 0 || surveyList[curCount].type.toInt() == 4 || surveyList[curCount].type.toInt()==6) { //답변이 선택형일 경우
                setFrag(surveyList[curCount].number.toInt())
            } else { //답변이 입력형일 경우
                setFrag(0)
            }
            // 뒤로가기를 누를 때마다 progressbar 역시 이전으로 되돌아가도록 설정
            //binding.progressbar.progress = currentFragment?.tag?.toIntOrNull() ?: 0
            // "다음" 버튼 텍스트를 "다음"으로 변경
            binding.nextstage.text = "다음"
        }
    }
    fun setFrag(fragNum: Int) {
        //프레그먼트를 교체해주는 코딩
        //supportFragmentManager는 프레그먼트를 관리하는 객체임
        val ft = supportFragmentManager.beginTransaction()
        currentFragment = when (fragNum) {
            -1 -> FragmentInform()
            0 -> NotypeFragment()
            2 -> Fragment2()
            3 -> Fragment3()
            4 -> Fragment4()
            5 -> Fragment5()
            6 -> Fragment6()
            7 -> Fragment7()
            8 -> Fragment8()
            10 -> Fragment10()
            else -> null
        }
        currentFragment?.let {
            ft.replace(R.id.main_frame, it, fragNum.toString())
            ft.addToBackStack(fragNum.toString())
            ft.commit()
        }
    }
}