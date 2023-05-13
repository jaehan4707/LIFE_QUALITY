package com.example.myapplication.question

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.*
import com.example.myapplication.MainActivity.Companion.answer
import com.example.myapplication.MainActivity.Companion.check_list
import com.example.myapplication.MainActivity.Companion.drinknum
import com.example.myapplication.MainActivity.Companion.smokenum
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
        var dbid =0
        lateinit var binding: QuestionMainpageBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //설문을 새로 시작할 때마다 초기화 해주어야 한다.
        curCount = 1
        binding = QuestionMainpageBinding.inflate(layoutInflater)
        setContentView(binding.root);
        var page = surveyList.get(0).number.toInt()
        Log.d("test", "${surveyList.size}")

        //프로그레스바 max값 정해주는 부분 (max는 설문의 개수만큼 되어야 한다.)
        binding.progressbar.max = surveyList.size
        tempSurvey = surveyList.get(0)
        answer.clear() //정답 배열 초기화.
        setFrag(page)
        //인적사항 입력할 때는 진행도가 1임
        binding.progressbar.progress = 0

        // "다음" 버튼 클릭 이벤트 구현 부분
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
                "Drink"->{
                    Log.d("test","현재 카운트 : $curCount, dringk num : ${drinknum}")
                    if (Id == 0) { //없음을 눌렀을때
                        //다음 화면 진행하지 않고 jump 해야함.
                        Log.d("test", "다음 화면을 진행하지 않습니다")
                        var num = curCount
                        while(num!= drinknum){
                            answer.add(Id)
                            curCount++
                            binding.progressbar.progress++
                            num++
                        }
                        Id = -1
                    }

                    if (curCount == surveyList.size) {
                        var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                        startActivity(intent)
                    } else {
                        //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                        tempSurvey = surveyList.get(curCount)
                        curCount++
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
                "Smoke"->{
                    if (Id == 0) { //모름을 눌렀을때
                        //다음 화면 진행하지 않고 jump 해야함.
                        Log.d("test", "다음 화면을 진행하지 않습니다")
                        for (i in 0 until smokenum - 1) {
                            answer.add(0)
                            curCount++
                            binding.progressbar.progress++
                        }
                        Id = -1
                    }
                    if (curCount == surveyList.size) {
                        var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                        startActivity(intent)
                    } else {
                        //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                        tempSurvey = surveyList.get(curCount)
                        curCount++
                        //마지막 문항일 경우에는 다음 버튼이 "결과보기"로 변경되어야 함
                        if (curCount == surveyList.size) {
                            binding.nextstage.text = "결과보기"
                        }
                        Log.d("curCount List", "curCount = $curCount")

                        if (tempSurvey.type.toInt() == 0 || tempSurvey.type.toInt()==4) { //답변이 선택형일 경우
                            setFrag(tempSurvey.number.toInt())
                        } else { //답변이 입력형일 경우
                            setFrag(0)
                        }
                    }
                    binding.progressbar.progress++
                }
                "Fall" -> {
                    Log.d("test","현재 문항 : ${curCount}")
                    if (Id == -1) {
                        Toast.makeText(this, "설문지를 선택하지 않았습니다!!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        answer.add(Id)
                    }
                    if (binding.progressbar.progress == 0 && Id == 2) { //경험이 없다고 눌렀을 4번으로 넘어가야함.
                        Log.d("test: ", "화면을 스킵해야합니다")
                        for (i in 1 .. 2) {
                            answer.add(0)
                            curCount++
                            binding.progressbar.progress++
                        }
                        Id = -1
                        //여기서 화면을 넘길까?
                    }
                    if (curCount==surveyList.size) {
                        Log.d("test","마지막 도착 :${curCount}")
                        var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                        startActivity(intent)
                    }
                    else {
                        //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                        Id = -1
                        tempSurvey = surveyList[curCount]
                        curCount++
                        //마지막 문항일 경우에는 다음 버튼이 "결과보기"로 변경되어야 함
                        if (curCount == surveyList.size) {
                            binding.nextstage.text = "결과보기"
                        }
                        Log.d("curCount List", "curCount = $curCount")

                        if (tempSurvey.type.toInt() == 0 || tempSurvey.type.toInt() == 4) { //답변이 선택형일 경우
                            setFrag(tempSurvey.number.toInt())
                        } else { //답변이 입력형일 경우
                            setFrag(0)
                        }
                        binding.progressbar.progress++
                    }
                }
                else -> {
                    if (Id == -1) {
                        Toast.makeText(this, "설문지를 선택하지 않았습니다!!", Toast.LENGTH_SHORT).show()
                    }
                    else if (Id != -1) {
                        answer.add(Id)
                        Id = -1
                        Log.d("test", "answer : ${answer[curCount - 1]}")
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

                            if (tempSurvey.type.toInt() == 0 || tempSurvey.type.toInt() == 5) { //답변이 선택형일 경우
                                setFrag(tempSurvey.number.toInt())
                            } else { //답변이 입력형일 경우
                                setFrag(0)
                            }
                        }
                        binding.progressbar.progress++
                    }
                }
            }
            /*
            if (Id == -1) {
                Toast.makeText(this, "설문지를 선택하지 않았습니다!!", Toast.LENGTH_SHORT).show()
            }
            else if (Id != -1) {
                answer.add(Id)
                Id = -1
                Log.d("test", "answer : ${answer[curCount - 1]}")
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
            if (tempSurvey.surveyType == "IPAQ") {
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
            else if (tempSurvey.surveyType == "Drink") {
                Log.d("test","현재 카운트 : ${curCount}, dringk num : ${drinknum}")
                if (Id == 0) { //없음을 눌렀을때
                    //다음 화면 진행하지 않고 jump 해야함.
                    Log.d("test", "다음 화면을 진행하지 않습니다")
                    var num =curCount
                    while(num!= drinknum){
                        answer.add(Id)
                        curCount++
                        binding.progressbar.progress++
                        num++
                    }
                    Id = -1
                }

                if (curCount == surveyList.size) {
                    var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                    startActivity(intent)
                } else {
                    //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                    tempSurvey = surveyList.get(curCount)
                    curCount++
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
            else if(tempSurvey.surveyType == "Smoke"){
                if (Id == 0) { //모름을 눌렀을때
                    //다음 화면 진행하지 않고 jump 해야함.
                    Log.d("test", "다음 화면을 진행하지 않습니다")
                    for (i in 0 until smokenum - 1) {
                        answer.add(0)
                        curCount++
                        binding.progressbar.progress++
                    }
                    Id = -1
                }
                if (curCount == surveyList.size) {
                    var intent = Intent(this@QuestionMainpage, ResultLayout::class.java)
                    startActivity(intent)
                } else {
                    //이제부터 버튼을 클릭할 떄마다 설문 type과 답변 개수에 따라 각각 다른 프레그먼트를 보여주어야 한다.
                    tempSurvey = surveyList.get(curCount)
                    curCount++
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
            프로그래스바 진행도 표시
            binding.progressbar.progress = curCount + 1
             */
        }
    }


    fun setFrag(fragNum: Int) {
        //프레그먼트를 교체해주는 코딩
        //supportFragmentManager는 프레그먼트를 관리하는 객체임
        val ft = supportFragmentManager.beginTransaction()
        when (fragNum) {
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