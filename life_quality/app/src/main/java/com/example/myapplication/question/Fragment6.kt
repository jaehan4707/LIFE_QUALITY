package com.example.myapplication.question

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.R
import com.example.myapplication.databinding.Type6FragmentBinding
import com.example.myapplication.question.QuestionMainpage.Companion.curCount
import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.viewModel.RadioViewModel

class Fragment6 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    private lateinit var sharedViewModel: RadioViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //var keyList= mutableListOf<String>()
        //var valueList = mutableListOf<String>()
        sharedViewModel = ViewModelProvider(requireActivity()).get(RadioViewModel::class.java)
        val keyList = arrayOfNulls<String>(6) // 크기가 6인 배열 생성
        val valueList = arrayOfNulls<String>(6) // 크기가 6인 배열 생성
        var binding = Type6FragmentBinding.inflate(layoutInflater) // 만들어준 xml파일을 binding한다.
        binding.type6Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type6Title.text = QuestionMainpage.tempSurvey.title.toString()
        var keyIndex = -1
        for ((key, value) in tempSurvey.answer) {
            if (tempSurvey.surveyType == "MouthHealth") {
                when (value) {
                    "항상" -> {
                        keyList[0] = key
                        valueList[0] = value
                    }
                    "매우 자주" -> {
                        keyList[1] = key
                        valueList[1] = value
                    }
                    "자주" -> {
                        keyList[2] = key
                        valueList[2] = value
                    }
                    "가끔" -> {
                        keyList[3] = key
                        valueList[3] = value
                    }
                    "거의 없음" -> {
                        keyList[4] = key
                        valueList[4] = value
                    }
                    "전혀 없음" -> {
                        keyList[5] = key
                        valueList[5] = value
                    }
                }
            } else { // 예외 처리: 배열의 크기를 벗어나는 경우는 추가하지 않음
                if (keyIndex < 5) {
                    keyIndex++
                    keyList[keyIndex] = key
                    valueList[keyIndex] = value
                }
            }
        }
        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        binding.rb4.text = valueList.get(3)
        binding.rb5.text = valueList.get(4)
        binding.rb6.text = valueList.get(5)
        var view = inflater.inflate(R.layout.type6_fragment, container, false)
        group = binding.groupF6
        group.setOnCheckedChangeListener { radioGroup, i ->
            sharedViewModel.setRadioButton(curCount,i)
            when(i){
                binding.rb1.id -> QuestionMainpage.Id = (keyList[0]!!.toDouble())
                binding.rb2.id -> QuestionMainpage.Id = (keyList[1]!!.toDouble())
                binding.rb3.id -> QuestionMainpage.Id = (keyList[2]!!.toDouble())
                binding.rb4.id -> QuestionMainpage.Id = (keyList[3]!!.toDouble())
                binding.rb5.id -> QuestionMainpage.Id = (keyList[4]!!.toDouble())
                binding.rb6.id->QuestionMainpage.Id = (keyList[5]!!.toDouble())
            }
        }
        val selectedRadioButtonId = sharedViewModel.getRadioButton(curCount)
        Log.d("problem","라디오체크할래 ${selectedRadioButtonId}")
        if(selectedRadioButtonId !=-1){
            group.check(selectedRadioButtonId)
        }
        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment6.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
